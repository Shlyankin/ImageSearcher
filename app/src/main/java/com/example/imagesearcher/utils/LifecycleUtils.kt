package com.example.imagesearcher.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> MutableLiveData<T>.liveData(): LiveData<T> = this

inline fun <T, K, R> LiveData<T>.combineNotNull(
    other: LiveData<K>,
    crossinline combine: (T, K) -> R
): LiveData<R> {
    return MediatorLiveData<R>().also {
        it.addSource(this) { thisValue ->
            other.value?.let { otherValue ->
                combine.invoke(thisValue, otherValue)
            }
        }
        it.addSource(other) { otherValue ->
            this.value?.let { thisValue ->
                combine.invoke(thisValue, otherValue)
            }
        }
    }
}

fun <T> Fragment.observe(liveData: LiveData<T>, onChanged: (T) -> Unit) {
    liveData.observe(this, Observer(onChanged))
}