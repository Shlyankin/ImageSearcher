package com.shlyankin.imagesearcher.utils

import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.File

fun Picasso.loadFileOrUrl(file: File, url: String): RequestCreator {
    return if (file.exists()) {
        load(file)
    } else {
        load(url)
    }
}

fun Picasso.loadFileOrUrl(path: String?, url: String): RequestCreator {
    return if (path != null && File(path).exists() && File(path).canWrite() && File(path).canRead()) {
        load(path)
    } else {
        load(url)
    }
}
