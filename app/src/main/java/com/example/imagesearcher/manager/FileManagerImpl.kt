package com.example.imagesearcher.manager

import android.content.Context
import android.os.Environment
import com.example.imagesearcher.domain.net.FileApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.*

class FileManagerImpl(
    private val fileApi: FileApi,
    private val context: Context
) : FileManager {

    private val downloadQueue = mutableMapOf<String, Job>()

    override val favouriteImageFile: File? by lazy {
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    }

    override fun downloadFile(url: String, filename: String): File {
        val localFile = File(favouriteImageFile, filename)
        val job = GlobalScope.launch {
            val response = fileApi.downloadFileByUrl(url)
            saveFile(localFile, response)
            downloadQueue.remove(url)
        }
        downloadQueue.put(url, job)
        return localFile
    }

    private fun saveFile(
        file: File,
        body: ResponseBody
    ) {
        try {

            var count: Int
            val data = ByteArray(1024 * 4)
            val fileSize = body.contentLength()
            val bis: InputStream = BufferedInputStream(body.byteStream(), 1024 * 8)

            val output: OutputStream = FileOutputStream(file)
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
            output.flush()
            output.close()
            bis.close()
        } catch (e: Exception) {
            file.delete()
        }
    }


    override fun stopDownloadFile(url: String) {
        downloadQueue.remove(url)
    }
}