package com.shlyankin.imagesearcher.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shlyankin.imagesearcher.databinding.FMainBinding
import com.shlyankin.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<FMainBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FMainBinding =
        FMainBinding.inflate(inflater, container, false)

    override fun FMainBinding.onInitViews() {
        viewPager.adapter = MainPagerAdapter(requireActivity())
        viewPager.isUserInputEnabled = false
        bottomNavigationBar.setOnItemSelectedListener {
            PagerFragments.findById(it.itemId)?.let {
                viewPager.setCurrentItem(it.ordinal, false)
                return@let true
            } ?: false
        }
    }
}