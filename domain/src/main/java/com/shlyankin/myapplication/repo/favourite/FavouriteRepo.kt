package com.shlyankin.myapplication.repo.favourite

import com.shlyankin.myapplication.database.model.FavouritePhoto
import kotlinx.coroutines.flow.Flow

interface FavouriteRepo {

    suspend fun addToFavourite(photo: FavouritePhoto)

    fun getAll(): Flow<List<FavouritePhoto>>

    suspend fun deleteFromFavourite(photo: FavouritePhoto)

    suspend fun get(id: String): FavouritePhoto?
}