package com.example.imagesearcher.domain.net

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    companion object {
        const val BASE_URL = "https://unsplash.com"
    }

    @GET("/search/photos")
    fun searchPhotos(
        query: String,
        page: Int = 1,
        @Query("per_page") perPage: Int = 10
    )
}