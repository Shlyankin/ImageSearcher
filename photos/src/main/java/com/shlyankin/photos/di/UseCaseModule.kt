package com.shlyankin.photos.di

import com.shlyankin.myapplication.datasource.PhotosDataSource
import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.photos.mapper.PhotoMapper
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCase
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCaseImpl
import com.shlyankin.photos.ui.usecase.photo.PhotosUseCase
import com.shlyankin.photos.ui.usecase.photo.PhotosUseCaseImpl
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
        photosDataSource: PhotosDataSource,
        photosMapper: PhotoMapper
    ): PhotosUseCase = PhotosUseCaseImpl(photosDataSource, photosMapper)

    @Singleton
    @Provides
    fun provideFavouriteUseCase(
        favouriteRepo: FavouriteRepo,
        photosMapper: PhotoMapper
    ): FavouriteUseCase = FavouriteUseCaseImpl(favouriteRepo, photosMapper)

}