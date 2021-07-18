package com.shlyankin.imagesearcher

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import dagger.hilt.android.HiltAndroidApp

@GlideModule
@HiltAndroidApp
class ImageSearcherApplication : Application()