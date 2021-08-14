package com.shlyankin.imagesearcher.domain.net

import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface UnsplashApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    @GET("/search/photos")
    suspend fun randomPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): SearchPhotosResponse


    @GET("/photos")
    suspend fun randomPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): List<PhotoEntity>
}