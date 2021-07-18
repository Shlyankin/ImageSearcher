package com.shlyankin.imagesearcher.domain.net

import com.shlyankin.imagesearcher.domain.model.PhotoEntity

class SearchPhotosResponse(
    val total: Int,
    val totalPages: Int,
    val results: List<PhotoEntity>
)