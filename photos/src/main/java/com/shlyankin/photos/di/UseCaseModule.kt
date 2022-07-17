package com.shlyankin.photos.di

import com.shlyankin.data.api.FavouriteRepo
import com.shlyankin.data.impl.datasource.PhotosDataSourceImpl
import com.shlyankin.domain.api.FavouriteUseCase
import com.shlyankin.domain.api.PhotosUseCase
import com.shlyankin.myapplication.FavouriteUseCaseImpl
import com.shlyankin.myapplication.PhotosUseCaseImpl
import com.shlyankin.photos.mapper.PhotoMapper
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
        photosDataSource: PhotosDataSourceImpl,
        photosMapper: PhotoMapper,
    ): PhotosUseCase = PhotosUseCaseImpl(photosDataSource, photosMapper)

    @Singleton
    @Provides
    fun provideFavouriteUseCase(
        favouriteRepo: com.shlyankin.data.api.FavouriteRepo,
        photosMapper: PhotoMapper,
    ): FavouriteUseCase = FavouriteUseCaseImpl(favouriteRepo, photosMapper)

}