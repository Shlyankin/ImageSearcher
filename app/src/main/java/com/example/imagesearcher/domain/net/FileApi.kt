package com.example.imagesearcher.domain.net

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface FileApi {

    @GET
    @Streaming
    suspend fun downloadFileByUrl(@Url url: String): ResponseBody

}