package com.shlyankin.myapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shlyankin.myapplication.net.FileApi
import com.shlyankin.myapplication.net.HeaderInterceptor
import com.shlyankin.myapplication.net.UnsplashApi
import com.shlyankin.util.utils.DEFAULT_DATETIME_FORMAT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetModule {

    @Singleton
    @Provides
    fun provideFileApi(okHttpClient: OkHttpClient): FileApi {
        return Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(FileApi::class.java)
    }

    @Singleton
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