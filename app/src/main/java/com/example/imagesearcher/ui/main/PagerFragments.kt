package com.example.imagesearcher.ui.main

import androidx.annotation.StringRes
import com.example.imagesearcher.R

enum class PagerFragments(@StringRes val fragmentNameId: Int) {
    PHOTOS(R.string.photos),
    FAVOURITE_PHOTOS(R.string.favourite_photos)
}