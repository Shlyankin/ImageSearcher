package com.shlyankin.util.net

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.NetworkError
            is HttpException -> {
                val code = throwable.code()
                ResultWrapper.HttpError(code, throwable)
            }
            else -> {
                ResultWrapper.GenericError(throwable)
            }
        }
    }
}