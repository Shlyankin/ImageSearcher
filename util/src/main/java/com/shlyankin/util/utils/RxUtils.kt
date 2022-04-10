package com.shlyankin.util.utils

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

const val RX_TAG = "RX"

infix fun Disposable.into(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}
