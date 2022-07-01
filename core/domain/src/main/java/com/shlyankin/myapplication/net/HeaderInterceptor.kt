package com.shlyankin.myapplication.net

import com.shlyankin.myapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID ${BuildConfig.UNSPLASH_ACCESS_KEY}")
                .build()
        )
    }
}