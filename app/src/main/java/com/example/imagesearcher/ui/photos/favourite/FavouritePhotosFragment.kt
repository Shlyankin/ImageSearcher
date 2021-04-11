package com.example.imagesearcher.ui.photos.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.imagesearcher.databinding.FPhotosBinding
import com.example.imagesearcher.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritePhotosFragment : BindingFragment<FPhotosBinding>() {

    private val photosViewModel by viewModels<FavouritePhotosViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FPhotosBinding.inflate(inflater, container, false)
}