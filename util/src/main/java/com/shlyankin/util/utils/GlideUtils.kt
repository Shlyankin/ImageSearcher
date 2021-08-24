package com.shlyankin.util.utils

import android.graphics.drawable.Drawable
import android.view.View
import com.shlyankin.util.glide.GlideApp
import com.shlyankin.util.glide.GlideRequest

fun GlideRequest<Drawable>.alternativeUri(
    root: View,
    errorUri: String?
): GlideRequest<Drawable> = apply {
    if (errorUri != null) {
        error(
            GlideApp
                .with(root)
                .load(errorUri)
        )
    }
}