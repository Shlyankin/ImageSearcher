package com.example.imagesearcher.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.imagesearcher.ui.photos.FavouritePhotosFragment
import com.example.imagesearcher.ui.photos.PhotosFragment

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return PagerFragments.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            PagerFragments.FAVOURITE_PHOTOS.ordinal -> FavouritePhotosFragment()
            PagerFragments.PHOTOS.ordinal -> PhotosFragment()
            else -> throw Exception("Check ${MainPagerAdapter::class}, cause adapters position is $position, but adapter has only ${PagerFragments.values().size} items")
        }
    }

    val tabNamesId: Array<Int> = PagerFragments.values().map { it.fragmentNameId }.toTypedArray()
}