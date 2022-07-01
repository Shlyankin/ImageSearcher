package com.shlyankin.myapplication.net

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

internal interface FileApi {

    @GET
    @Streaming
    fun downloadFileByUrl(@Url url: String): Single<ResponseBody>

}