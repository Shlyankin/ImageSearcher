package com.shlyankin.imagesearcher.di

import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.repo.FavouriteRepo
import com.shlyankin.imagesearcher.domain.repo.PhotoRepo
import com.shlyankin.imagesearcher.domain.usecase.FavouriteUseCase
import com.shlyankin.imagesearcher.domain.usecase.FavouriteUseCaseImpl
import com.shlyankin.imagesearcher.domain.usecase.PhotoUseCase
import com.shlyankin.imagesearcher.domain.usecase.PhotoUseCaseImpl
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
        photoRepo: PhotoRepo,
        photosMapper: PhotoMapper
    ): PhotoUseCase = PhotoUseCaseImpl(photoRepo, photosMapper)

    @Singleton
    @Provides
    fun provideFavouriteUseCase(
        photoRepo: PhotoRepo,
        favouriteRepo: FavouriteRepo,
        photosMapper: PhotoMapper
    ): FavouriteUseCase = FavouriteUseCaseImpl(photoRepo, favouriteRepo, photosMapper)

}