package com.shlyankin.util.net

import android.util.Log
import com.shlyankin.util.utils.emptySuspendArgFun
import com.shlyankin.util.utils.emptySuspendFun
import com.shlyankin.util.utils.emptySuspendTwiceArgsFun
import retrofit2.HttpException

/**
 *  Suspend functional parameters with default values are not yet supported in inline functions
 *  поэтому для оптимизации необходимо обернуть вызовы с меньшим количеством аргументов
 */
sealed class ResultWrapper<out T> {

    data class Success<out T>(val value: T) : ResultWrapper<T>()

    data class HttpError(
        val code: Int? = null,
        val exception: HttpException? = null,
    ) : ResultWrapper<Nothing>()

    data class GenericError(
        val exception: Throwable? = null,
    ) : ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()

    fun takeValue(): T? = if (this is Success) value else null

    suspend inline fun onSuccess(crossinline onSuccess: suspend (T) -> Unit): ResultWrapper<T> {
        if (this is Success) {
            onSuccess.invoke(this.value)
        }
        return this
    }

    suspend inline fun onError(crossinline onError: suspend (e: Throwable?) -> Unit): ResultWrapper<T> {
        if (this is GenericError) {
            onError.invoke(this.exception)
        }
        if (this is HttpError) {
            onError.invoke(this.exception)
        }
        if (this is NetworkError) {
            onError.invoke(null)
        }
        return this
    }

    // оптимизация
    suspend inline fun checkResult(
        crossinline onSuccess: (suspend (T) -> Unit),
    ) {
        checkResult(
            onSuccess = onSuccess,
            onHttpError = emptySuspendTwiceArgsFun(),
            onGenericError = emptySuspendArgFun(),
            onConnectionError = emptySuspendFun()
        )
    }

    // оптимизация
    suspend inline fun checkResult(
        crossinline onSuccess: (suspend (T) -> Unit),
        crossinline onError: (suspend (e: Throwable?) -> Unit),
    ) {
        checkResult(
            onSuccess = onSuccess,
            onHttpError = { _, e ->
                onError.invoke(e)
            },
            onGenericError = { e ->
                onError.invoke(e)
            },
            onConnectionError = {
                onError.invoke(null)
            }
        )
    }

    // оптимизация
    suspend inline fun checkResult(
        crossinline onSuccess: (suspend (T) -> Unit),
        crossinline onHttpError: (suspend (code: Int?, HttpException?) -> Unit),
        crossinline onGenericError: (suspend (Throwable?) -> Unit),
        crossinline onConnectionError: (suspend () -> Unit),
    ) {
        when (this) {
            is Success<T> -> {
                Log.i("checkResult", "success http request with result: $value")
                onSuccess.invoke(value)
            }
            is GenericError -> {
                Log.e(
                    "checkResult",
                    "failed http request with exception: $exception, message: ${exception?.message}"
                )
                onGenericError.invoke(exception)
            }
            is HttpError -> {
                Log.e(
                    "checkResult",
                    "failed http request with code: $code, exception: $exception, message: ${exception?.message}"
                )
                onHttpError.invoke(code, exception)
            }
            is NetworkError -> {
                Log.e(
                    "checkResult",
                    "failed http request. network error"
                )
                onConnectionError.invoke()
            }
        }
    }
}