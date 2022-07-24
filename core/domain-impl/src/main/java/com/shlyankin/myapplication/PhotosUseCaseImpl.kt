package com.shlyankin.myapplication

import com.shlyankin.data.api.PhotoRepo
import com.shlyankin.domain.api.PhotosUseCase

class PhotosUseCaseImpl(
    photoRepo: PhotoRepo,
) : PhotosUseCase {

    override val photos = photoRepo.getPhotos()
}