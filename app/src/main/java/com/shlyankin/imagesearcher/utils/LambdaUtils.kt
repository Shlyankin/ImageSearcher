package com.shlyankin.imagesearcher.utils

fun emptySuspendFun() = suspend {}

fun <T> emptySuspendArgFun(): suspend (T) -> Unit = { _: T -> }

fun <T, B> emptySuspendTwiceArgsFun(): suspend (T, B) -> Unit = { _: T, _: B -> }

fun emptyFun() = {}

fun <T> emptyArgFun() = { _: T -> }

fun <T, B> emptyTwiceArgsFun(): (T, B) -> Unit = { _: T, _: B -> }