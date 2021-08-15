package com.shlyankin.imagesearcher.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Fragment.observeLatest(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        flow.collectLatest(action)
    }
}
