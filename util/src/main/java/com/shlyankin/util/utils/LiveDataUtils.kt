package com.shlyankin.util.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData


inline fun <T> Fragment.observe(
    liveData: LiveData<T>, crossinline body: (T) -> Unit
) {
    liveData.observe(this.viewLifecycleOwner) {
        body(it)
    }
}

inline fun <T> AppCompatActivity.observe(
    liveData: LiveData<T>, crossinline body: (T) -> Unit
) {
    liveData.observe(this) {
        body(it)
    }
}