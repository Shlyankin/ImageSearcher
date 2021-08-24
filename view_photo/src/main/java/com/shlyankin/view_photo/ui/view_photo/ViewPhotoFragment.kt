package com.shlyankin.view_photo.ui.view_photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shlyankin.util.BindingFragment
import com.shlyankin.util.utils.observe
import com.shlyankin.view_photo.databinding.FViewPhotoBinding

class ViewPhotoFragment : BindingFragment<FViewPhotoBinding>() {

    private val viewModel by viewModels<ViewPhotoViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FViewPhotoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo: receive args
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.run {
            observe(photo) {
                binding.run {
                    // todo: handle ui
                }
            }
        }
    }
}