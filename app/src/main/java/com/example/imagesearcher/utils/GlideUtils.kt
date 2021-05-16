package com.example.imagesearcher.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import java.io.File

fun RequestManager.loadFileOrUrl(file: File, url: String): RequestBuilder<Drawable> {
    return if (file.exists()) {
        load(file)
    } else {
        load(url)
    }
}

fun RequestManager.loadFileOrUrl(path: String?, url: String): RequestBuilder<Drawable> {
    return if (path != null && File(path).exists()) {
        load(File(path))
    } else {
        load(url)
    }
}