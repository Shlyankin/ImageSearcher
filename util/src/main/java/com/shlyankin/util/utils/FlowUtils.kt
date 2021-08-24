package com.shlyankin.util.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

// cast Shared, State Flow to Flow
fun <T> Flow<T>.flow(): Flow<T> = this

suspend fun MutableSharedFlow<Unit>.emitEvent() = emit(Unit)