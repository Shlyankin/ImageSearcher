package com.shlyankin.imagesearcher.domain.net

import android.util.Log
import retrofit2.HttpException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class HttpError(val code: Int? = null, val exception: HttpException? = null) :
        ResultWrapper<Nothing>()

    data class GenericError(val exception: Throwable? = null) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()

    suspend fun checkResult(
        onHttpError: (suspend (code: Int?, HttpException?) -> Unit)? = null,
        onGenericError: (suspend (Throwable?) -> Unit)? = null,
        onConnectionError: (suspend () -> Unit)? = null,
        onSuccess: (suspend (T) -> Unit)? = null
    ) {
        when (this) {
            is Success<T> -> {
                Log.i("checkResult", "success http request with result: $value")
                onSuccess?.invoke(value)
            }
            is GenericError -> {
                Log.e(
                    "checkResult",
                    "failed http request with exception: $exception, message: ${exception?.message}"
                )
                onGenericError?.invoke(exception)
            }
            is HttpError -> {
                Log.e(
                    "checkResult",
                    "failed http request with code: $code, exception: $exception, message: ${exception?.message}"
                )
                onHttpError?.invoke(code, exception)
            }
            is NetworkError -> {
                Log.e(
                    "checkResult",
                    "failed http request. network error"
                )
                onConnectionError?.invoke()
            }
        }
    }
}