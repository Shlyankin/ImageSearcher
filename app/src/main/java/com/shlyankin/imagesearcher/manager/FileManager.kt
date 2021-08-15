package com.shlyankin.imagesearcher.manager

import java.io.File

interface FileManager {
    val favouriteImageFile: File?
    suspend fun suspendDownloadFile(url: String, filename: String): File
    fun stopDownloadFile(url: String)
    fun downloadFile(url: String, filename: String): File
}