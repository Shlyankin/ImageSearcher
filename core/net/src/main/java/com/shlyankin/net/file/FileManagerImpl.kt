package com.shlyankin.net.file

import android.content.Context
import android.os.Environment
import com.shlyankin.net.FileApi
import com.shlyankin.util.net.safeApiCall
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import java.io.*

class FileManagerImpl(
    private val fileApi: FileApi,
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
) : FileManager {

    private val downloadQueue = mutableMapOf<String, Job>()

    override val favouriteImageFile: File? by lazy {
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    }

    @DelicateCoroutinesApi
    override fun downloadFile(url: String, filename: String): File {
        val localFile = File(favouriteImageFile, filename).apply {
            parentFile?.mkdirs()
            createNewFile()
        }
        val job = GlobalScope.launch(ioDispatcher) {
            safeApiCall {
                fileApi.downloadFileByUrl(url)
            }.checkResult { response ->
                saveFile(localFile, response)
                downloadQueue.remove(url)
            }
        }
        downloadQueue[url] = job
        return localFile
    }

    @DelicateCoroutinesApi
    override suspend fun suspendDownloadFile(url: String, filename: String): File {
        try {
            val job = GlobalScope.async(ioDispatcher) {
                val localFile = File(favouriteImageFile, filename)
                localFile.runCatching {
                    parentFile?.mkdirs()
                    createNewFile()
                }
                safeApiCall {
                    fileApi.downloadFileByUrl(url)
                }.checkResult { response ->
                    saveFile(localFile, response)
                }
                return@async localFile
            }
            downloadQueue[url] = job
            return job.await()
        } finally {
            downloadQueue.remove(url)
        }
    }

    private fun saveFile(
        file: File,
        body: ResponseBody
    ) {
        var bis: InputStream? = null
        var output: OutputStream? = null
        try {
            var count: Int
            val data = ByteArray(1024 * 4)
            val fileSize = body.contentLength()
            bis = BufferedInputStream(body.byteStream(), 1024 * 8)
            output = FileOutputStream(file)
            var total: Long = 0
            var progress = 0
            if (fileSize < 0) {
                throw Exception("Incorrect file")
            }
            while (bis.read(data).also { count = it } != -1) {
                output.write(data, 0, count)
                total += count.toLong()
                val progressCount = total * 100 / fileSize
                if (progress != progressCount.toInt()) {
                    progress = progressCount.toInt()
                }
            }
        } catch (e: Exception) {
            file.delete()
        } finally {
            output?.flush()
            output?.close()
            bis?.close()
        }
    }


    override fun stopDownloadFile(url: String) {
        downloadQueue.remove(url)
    }
}