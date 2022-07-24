package com.shlyankin.imagesearcher.di

import com.shlyankin.data.api.FavouriteRepo
import com.shlyankin.data.api.PhotoRepo
import com.shlyankin.domain.api.FavouriteUseCase
import com.shlyankin.domain.api.PhotosUseCase
import com.shlyankin.domain.api.ViewPhotoUseCase
import com.shlyankin.myapplication.FavouriteUseCaseImpl
import com.shlyankin.myapplication.PhotosUseCaseImpl
import com.shlyankin.myapplication.ViewPhotoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class UseCaseModule {

    @Singleton
    @Provides
    fun providePhotoUseCase(
        photoRepo: PhotoRepo,
    ): PhotosUseCase = PhotosUseCaseImpl(photoRepo)

    @Singleton
    @Provides
    fun provideFavouriteUseCase(
        favouriteRepo: FavouriteRepo,
    ): FavouriteUseCase = FavouriteUseCaseImpl(favouriteRepo)

    @Singleton
    @Provides
    fun provideViewPhotoUseCase(
        photoRepo: PhotoRepo,
        favouriteRepo: FavouriteRepo,
    ): ViewPhotoUseCase = ViewPhotoUseCaseImpl(photoRepo, favouriteRepo)
}