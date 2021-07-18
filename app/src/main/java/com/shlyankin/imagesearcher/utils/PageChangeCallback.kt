package com.shlyankin.imagesearcher.utils

import androidx.viewpager2.widget.ViewPager2

typealias OnPageScrolled = (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit
typealias OnPageSelected = (position: Int) -> Unit
typealias OnPageScrollStateChanged = (state: Int) -> Unit

class PageChangeCallback(
    private val onPageScrolled: OnPageScrolled? = null,
    private val onPageSelected: OnPageSelected? = null,
    private val onPageScrollStateChamged: OnPageScrollStateChanged? = null,
) : ViewPager2.OnPageChangeCallback() {

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        onPageSelected?.invoke(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        onPageScrollStateChamged?.invoke(state)
    }
}