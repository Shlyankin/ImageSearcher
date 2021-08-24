package com.shlyankin.util.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Fragment.observe(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        flow.collect(action)
    }
}

fun <T> Fragment.observeLatest(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        flow.collectLatest(action)
    }
}

fun <T> AppCompatActivity.observe(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        flow.collect(action)
    }
}

fun <T> AppCompatActivity.observeLatest(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        flow.collectLatest(action)
    }
}
