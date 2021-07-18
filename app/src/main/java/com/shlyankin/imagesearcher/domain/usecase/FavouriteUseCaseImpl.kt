package com.shlyankin.imagesearcher.domain.usecase

import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.domain.repo.FavouriteRepo
import com.shlyankin.imagesearcher.domain.repo.PhotoRepo
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class FavouriteUseCaseImpl(
    private val photoRepo: PhotoRepo,
    private val favouriteRepo: FavouriteRepo,
    private val photosMapper: PhotoMapper
) : FavouriteUseCase {

    private val rawFavouritePhotos = favouriteRepo.getAll()
    override val favouritePhotos = rawFavouritePhotos.map {
        photosMapper.convertFromFavouritePhotoToUiPhoto(it).reversed()
    }

    override suspend fun changePhotoFavouriteState(photo: UiPhoto) {
        photoRepo.getPhoto(photo.id)?.let { changedPhotoEntity: PhotoEntity ->
            val inFavourite =
                rawFavouritePhotos.firstOrNull()?.find { it.id == changedPhotoEntity.id }
            if (inFavourite != null) {
                favouriteRepo.deleteFromFavourite(inFavourite)
            } else {
                favouriteRepo.addToFavourite(changedPhotoEntity)
            }
        }
    }

//    override suspend fun deleteFromFavourites(photo: PhotoEntity) {
//        favouriteRepo.deleteFromFavourite(photo)
//    }
//
//    override suspend fun addToFavourite(photo: PhotoEntity) {
//        favouriteRepo.addToFavourite(photo)
//    }
}