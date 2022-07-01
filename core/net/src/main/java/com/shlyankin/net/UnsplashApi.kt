package com.shlyankin.net

import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.net.model.SearchPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UnsplashApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    /**
     * todo: апи поиска фото. Сейчас функционал не реализован
     */
    @GET("/search/photos")
    suspend fun randomPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ): SearchPhotosResponse


    @GET("/photos")
    suspend fun randomPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): List<PhotoResponse>

    @GET("/photos/{photoId}")
    suspend fun getPhoto(@Path(value = "photoId", encoded = true) photoId: String): PhotoResponse
}