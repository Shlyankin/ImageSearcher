package com.example.imagesearcher.di

import com.example.imagesearcher.domain.net.FileApi
import com.example.imagesearcher.domain.net.HeaderInterceptor
import com.example.imagesearcher.domain.net.UnsplashApi
import com.example.imagesearcher.utils.DEFAULT_DATETIME_FORMAT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    fun provideFileApi(okHttpClient: OkHttpClient): FileApi {
        return Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(FileApi::class.java)
    }

    @Provides
    fun provideUnsplashApi(okHttpClient: OkHttpClient, gson: Gson): UnsplashApi {
        return Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UnsplashApi::class.java)
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().setDateFormat(DEFAULT_DATETIME_FORMAT).create()

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }
}