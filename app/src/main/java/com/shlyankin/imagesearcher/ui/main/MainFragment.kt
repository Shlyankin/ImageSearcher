package com.shlyankin.imagesearcher.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.shlyankin.imagesearcher.databinding.FMainBinding
import com.shlyankin.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<FMainBinding>() {

    private val pagerAdapter by lazy { MainPagerAdapter(requireActivity()) }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FMainBinding =
        FMainBinding.inflate(inflater, container, false)

    override fun FMainBinding.onInitViews() {
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = getString(pagerAdapter.tabNamesId[position])
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }
}