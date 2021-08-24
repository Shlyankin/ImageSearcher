package com.shlyankin.photos.ui.photos.favourite

import androidx.fragment.app.viewModels
import com.shlyankin.photos.ui.photos.PhotosFragment
import com.shlyankin.photos.ui.photos.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritePhotosFragment : PhotosFragment() {
    override val viewModel: PhotosViewModel by viewModels<FavouritePhotosViewModel>()
}