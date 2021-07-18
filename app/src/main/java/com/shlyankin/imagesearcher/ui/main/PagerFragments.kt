package com.shlyankin.imagesearcher.ui.main

import androidx.annotation.StringRes
import com.shlyankin.imagesearcher.R

enum class PagerFragments(@StringRes val fragmentNameId: Int) {
    PHOTOS(R.string.photos),
    FAVOURITE_PHOTOS(R.string.favourite_photos)
}