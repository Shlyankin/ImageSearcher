package com.example.imagesearcher.manager

import java.io.File

interface FileManager {
    val favouriteImageFile: File?
    fun downloadFile(url: String, filename: String): File
    fun stopDownloadFile(url: String)
}