package com.shlyankin.myapplication.net.model

/**
 * todo: ответ апи поиска фото. Сейчас функционал не реализован
 */
internal data class SearchPhotosResponse(
    val total: Int,
    val totalPages: Int,
    val results: List<PhotoResponse>,
)