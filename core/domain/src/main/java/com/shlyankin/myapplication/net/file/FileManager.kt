package com.shlyankin.myapplication.net.file

import java.io.File

interface FileManager {
    val favouriteImageFile: File?
    fun stopDownloadFile(url: String)
    fun downloadFile(url: String, filename: String): File
}