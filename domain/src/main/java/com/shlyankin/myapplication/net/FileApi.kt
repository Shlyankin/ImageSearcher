package com.shlyankin.myapplication.net

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

internal interface FileApi {

    @GET
    @Streaming
    suspend fun downloadFileByUrl(@Url url: String): ResponseBody

}