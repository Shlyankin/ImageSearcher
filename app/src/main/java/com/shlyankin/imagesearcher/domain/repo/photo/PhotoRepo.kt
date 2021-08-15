package com.shlyankin.imagesearcher.domain.repo.photo

import com.shlyankin.imagesearcher.domain.model.NetPhoto
import com.shlyankin.imagesearcher.domain.net.ResultWrapper

interface PhotoRepo {
    suspend fun getPhotos(page: Int): ResultWrapper<List<NetPhoto>>
}