package com.shlyankin.photos.mapper

import com.shlyankin.domain.models.*
import com.shlyankin.photos.model.PresPhoto
import com.shlyankin.photos.model.PresPhotoUrls
import com.shlyankin.photos.model.PresProfileImage
import com.shlyankin.photos.model.PresUser


fun PresPhoto.toDomain() = PhotoDomain(
    id = id,
    createdAt = createdAt,
    description = description,
    urls = urls.toDomain(),
    user.toDomain()
)

fun PresPhotoUrls.toDomain() = PhotoUrlsDomain(
    raw = raw,
    full = full,
    regular = regular,
    small = small,
    thumb = thumb
)

fun PresUser.toDomain() = UserDomain(
    name = name, profileImage = profileImage?.toDomain()
)

fun PresProfileImage.toDomain() = ProfileImageDomain(
    small = small,
    medium = medium,
    large = large
)

fun List<FavouritePhotoDomain>.toPres() = map { domain -> domain.toPres() }

fun PhotoDomain.toPres() = PresPhoto(
    id = id,
    createdAt = createdAt,
    description = description,
    urls = urls.toPres(),
    user = user.toPres(),
    localPath = null,
    isFavourite = false
)

fun FavouritePhotoDomain.toPres() = PresPhoto(
    id = id,
    createdAt = createdAt,
    description = description,
    urls = urls.toPres(),
    user = user.toPres(),
    localPath = localPath,
    isFavourite = true
)

fun PhotoUrlsDomain.toPres() = PresPhotoUrls(
    raw = raw,
    full = full,
    regular = regular,
    small = small,
    thumb = thumb
)

fun UserDomain.toPres() = PresUser(
    name = name, profileImage = profileImage?.toPres()
)

private fun ProfileImageDomain.toPres() = PresProfileImage(
    small, medium, large
)
