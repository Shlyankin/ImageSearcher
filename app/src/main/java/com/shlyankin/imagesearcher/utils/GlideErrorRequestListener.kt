package com.shlyankin.imagesearcher.utils

import android.util.Log
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideErrorRequestListener<R>(private val onLoadFailed: () -> Boolean) : RequestListener<R> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<R>?,
        isFirstResource: Boolean
    ): Boolean {
        Log.e("GLIDE", "onLoadFailed")
        return onLoadFailed.invoke()
    }

    override fun onResourceReady(
        resource: R,
        model: Any?,
        target: Target<R>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        Log.i("GLIDE", "onResourceReady")
        return false
    }
}