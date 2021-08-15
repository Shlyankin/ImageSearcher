package com.shlyankin.imagesearcher.domain.net

import com.shlyankin.imagesearcher.domain.model.NetPhoto

/**
 * todo: ответ апи поиска фото. Сейчас функционал не реализован
 */
class SearchPhotosResponse(
    val total: Int,
    val totalPages: Int,
    val results: List<NetPhoto>
)