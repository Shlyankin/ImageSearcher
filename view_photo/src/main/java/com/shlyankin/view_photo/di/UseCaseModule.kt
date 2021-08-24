package com.shlyankin.view_photo.di

import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import com.shlyankin.view_photo.mapper.PhotoMapper
import com.shlyankin.view_photo.ui.usecase.ViewPhotoUseCase
import com.shlyankin.view_photo.ui.usecase.ViewPhotoUseCaseImpl
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
    fun provideViewPhotoUseCase(
        photoRepo: PhotoRepo,
        favouriteRepo: FavouriteRepo,
        photoMapper: PhotoMapper
    ): ViewPhotoUseCase = ViewPhotoUseCaseImpl(photoRepo, favouriteRepo, photoMapper)

}