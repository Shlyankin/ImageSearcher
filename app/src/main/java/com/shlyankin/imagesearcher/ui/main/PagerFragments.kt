package com.shlyankin.imagesearcher.ui.main

import androidx.annotation.IdRes
import com.shlyankin.imagesearcher.R

enum class PagerFragments(@IdRes val itemId: Int) {
    PHOTOS(R.id.random_photos),
    FAVOURITE_PHOTOS(R.id.favourite_photos);

    companion object {
        fun findById(@IdRes itemId: Int): PagerFragments? {
            return when (itemId) {
                PHOTOS.itemId -> PHOTOS
                FAVOURITE_PHOTOS.itemId -> FAVOURITE_PHOTOS
                else -> null
            }
        }
    }
}