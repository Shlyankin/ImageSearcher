package com.shlyankin.util.utils

fun emptySuspendFun() = suspend {}

fun <T> emptySuspendArgFun(): suspend (T) -> Unit = { _: T -> }

fun <T, B> emptySuspendTwiceArgsFun(): suspend (T, B) -> Unit = { _: T, _: B -> }

fun emptyFun() = {}
