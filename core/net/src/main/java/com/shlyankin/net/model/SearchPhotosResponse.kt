package com.shlyankin.net.model

/**
 * todo: ответ апи поиска фото. Сейчас функционал не реализован
 */
data class SearchPhotosResponse(
    val total: Int,
    val totalPages: Int,
    val results: List<PhotoResponse>,
)