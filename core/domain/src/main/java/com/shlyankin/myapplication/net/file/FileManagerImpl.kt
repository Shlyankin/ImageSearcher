package com.shlyankin.myapplication.net.file

import android.content.Context
import android.os.Environment
import com.shlyankin.myapplication.net.FileApi
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.*

internal class FileManagerImpl(
    private val fileApi: FileApi,
    private val context: Context,
) : FileManager {

    private val downloadQueue = mutableMapOf<String, Disposable>()

    override val favouriteImageFile: File? by lazy {
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    }

    override fun downloadFile(url: String, filename: String): File {
        val localFile = File(favouriteImageFile, filename).apply {
            parentFile?.mkdirs()
            createNewFile()
        }
        val disposable = fileApi.downloadFileByUrl(url)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                saveFile(localFile, response)
                downloadQueue.remove(url)
            }.subscribe()

        downloadQueue[url] = disposable
        return localFile
    }

    private fun saveFile(
        file: File,
        body: ResponseBody,
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
        downloadQueue[url]?.dispose()
        downloadQueue.remove(url)
    }
}