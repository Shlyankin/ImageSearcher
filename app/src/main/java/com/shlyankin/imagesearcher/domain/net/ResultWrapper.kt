package com.shlyankin.imagesearcher.domain.net

import android.util.Log
import com.shlyankin.imagesearcher.utils.emptySuspendArgFun
import com.shlyankin.imagesearcher.utils.emptySuspendFun
import com.shlyankin.imagesearcher.utils.emptySuspendTwiceArgsFun
import retrofit2.HttpException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class HttpError(val code: Int? = null, val exception: HttpException? = null) :
        ResultWrapper<Nothing>()

    data class GenericError(val exception: Throwable? = null) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()

    suspend inline fun checkResult(
        crossinline onSuccess: (suspend (T) -> Unit)
    ) {
        checkResult(
            onSuccess = onSuccess,
            onHttpError = emptySuspendTwiceArgsFun(),
            onGenericError = emptySuspendArgFun(),
            onConnectionError = emptySuspendFun()
        )
    }

    suspend inline fun checkResult(
        crossinline onSuccess: (suspend (T) -> Unit),
        crossinline onError: (suspend (e: Throwable?) -> Unit)
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

    /**
     * todo: Suspend functional parameters with default values are not yet supported in inline functions
     *  поэтому для оптимизации необходимо обернуть вызовы с меньшим количеством аргументов
     */
    suspend inline fun checkResult(
        crossinline onSuccess: (suspend (T) -> Unit),
        crossinline onHttpError: (suspend (code: Int?, HttpException?) -> Unit),
        crossinline onGenericError: (suspend (Throwable?) -> Unit),
        crossinline onConnectionError: (suspend () -> Unit)
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