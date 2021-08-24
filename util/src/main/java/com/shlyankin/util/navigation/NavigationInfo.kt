package com.shlyankin.util.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf

sealed class NavigationInfo {
    class NavigationTo(@IdRes val destionationId: Int, val bundle: Bundle = bundleOf()) :
        NavigationInfo()

    class NavigationPopTo(@IdRes val destionationId: Int) : NavigationInfo()
    object NavigationPop : NavigationInfo()
}