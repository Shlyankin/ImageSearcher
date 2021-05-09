package com.example.imagesearcher.domain.usecase

import android.util.Log
import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.domain.repo.PhotoRepo
import kotlinx.coroutines.flow.MutableStateFlow

class PhotoUseCaseImpl(
    private val photoRepo: PhotoRepo
) : PhotoUseCase {

    override val photos = MutableStateFlow<List<PhotoEntity>>(emptyList())

    override suspend fun updatePhotos(page: Int) {
        try {
            photos.value = photoRepo.getPhotos(page)
        } catch (e: Exception) {
            Log.e("", "$e")
        }
    }
}