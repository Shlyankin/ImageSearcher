package com.shlyankin.imagesearcher

import kotlin.reflect.KClass


inline fun <reified T : Any> KClass<T>.screenName(): String {
    return this.qualifiedName ?: throw Exception("Check Screen Name")
}
