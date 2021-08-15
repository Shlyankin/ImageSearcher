package com.shlyankin.imagesearcher.di

import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.datasource.photo.PhotosDataSource
import com.shlyankin.imagesearcher.domain.repo.favourite.FavouriteRepo
import com.shlyankin.imagesearcher.domain.usecase.favourite.FavouriteUseCase
import com.shlyankin.imagesearcher.domain.usecase.favourite.FavouriteUseCaseImpl
import com.shlyankin.imagesearcher.domain.usecase.photo.PhotosUseCase
import com.shlyankin.imagesearcher.domain.usecase.photo.PhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

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