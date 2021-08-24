package com.shlyankin.view_photo.ui.view_photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.shlyankin.util.BindingFragment
import com.shlyankin.util.glide.GlideApp
import com.shlyankin.util.utils.alternativeUri
import com.shlyankin.util.utils.observe
import com.shlyankin.view_photo.R
import com.shlyankin.view_photo.databinding.FViewPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPhotoFragment : BindingFragment<FViewPhotoBinding>() {

    companion object {
        private const val PHOTO_ID = "PHOTO_ID"

        fun getBundle(photoId: String) = bundleOf(PHOTO_ID to photoId)
    }

    private val viewModel by viewModels<ViewPhotoViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FViewPhotoBinding.inflate(inflater, container, false)

    override fun FViewPhotoBinding.onInitViews() {
        addToFavourite.setOnClickListener {
            viewModel.addToFavouriteClicked()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveBundleArgs()
        observeViewModel()
    }

    private fun receiveBundleArgs() {
        arguments?.run {
            getString(PHOTO_ID)?.let { photoId ->
                viewModel.onArgumentsReceived(photoId)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.run {
            observe(photo) { uiPhoto ->
                binding.run {
                    if (photo.drawable == null) {
                        // load picture
                        GlideApp.with(root)
                            .load(uiPhoto.localPath)
                            .alternativeUri(root, uiPhoto.url)
                            .into(photo)
                    }
                    if (authorAvatar.drawable == null) {
                        // load avatar
                        GlideApp.with(root).load(uiPhoto.user.photoUrl)
                            .error(R.drawable.ic_person_outline)
                            .into(authorAvatar)
                    }
                    author.text = getString(R.string.author, uiPhoto.user.name)
                    photoDescription.text = getString(
                        R.string.description,
                        uiPhoto.description ?: getString(R.string.has_not)
                    )
                    createdAt.text = getString(R.string.created, uiPhoto.createdAt)
                    addToFavourite.setImageResource(
                        if (uiPhoto.isFavourite) {
                            R.drawable.ic_favourite
                        } else {
                            R.drawable.ic_empty_favourite
                        }
                    )
                }
            }
        }
    }
}