package com.shlyankin.imagesearcher

import android.app.Application
import com.bumptech.glide.module.AppGlideModule
import dagger.hilt.android.HiltAndroidApp

class T : AppGlideModule()

@HiltAndroidApp
class ImageSearcherApplication : Application()