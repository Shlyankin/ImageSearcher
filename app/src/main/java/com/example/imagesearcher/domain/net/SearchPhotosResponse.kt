package com.example.imagesearcher.domain.net

import com.example.imagesearcher.domain.model.Photo

class SearchPhotosResponse(
    val total: Int,
    val totalPages: Int,
    val results: List<Photo>
)