package com.example.imagesearcher.di

import com.example.imagesearcher.domain.repo.PhotoRepo
import com.example.imagesearcher.domain.usecase.PhotoUseCase
import com.example.imagesearcher.domain.usecase.PhotoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun providePhotoUseCase(photoRepo: PhotoRepo): PhotoUseCase = PhotoUseCaseImpl(photoRepo)

}