package com.shlyankin.imagesearcher.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.liveData(): LiveData<T> = this

inline fun <T, K, R> LiveData<T>.combineNotNull(
    liveData: LiveData<K>,
    crossinline combination: (T, K) -> R,
): LiveData<R> {
    return MediatorLiveData<R>().also { mediator ->
        mediator.addSource(this) {
            val otherValue = liveData.value ?: return@addSource
            mediator.value = combination(it, otherValue)
        }
        mediator.addSource(liveData) {
            val otherValue = this.value ?: return@addSource
            mediator.value = combination(otherValue, it)
        }
    }
}

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline block: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, { block(it) })