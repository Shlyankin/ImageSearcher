package com.shlyankin.myapplication.net

import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.myapplication.net.model.SearchPhotosResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


internal interface UnsplashApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    /**
     * todo: апи поиска фото. Сейчас функционал не реализован
     */
    @GET("/search/photos")
    fun randomPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Single<SearchPhotosResponse>


    @GET("/photos")
    fun randomPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Single<List<PhotoResponse>>

    @GET("/photos/{photoId}")
    fun getPhoto(@Path(value = "photoId", encoded = true) photoId: String): Single<PhotoResponse>
}