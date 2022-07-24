package com.shlyankin.view_photo.mapper

import com.shlyankin.domain.models.PhotoDomain
import com.shlyankin.domain.models.PhotoUrlsDomain
import com.shlyankin.domain.models.ProfileImageDomain
import com.shlyankin.domain.models.UserDomain
import com.shlyankin.view_photo.model.PresPhoto
import com.shlyankin.view_photo.model.PresPhotoUrls
import com.shlyankin.view_photo.model.PresProfileImage
import com.shlyankin.view_photo.model.PresUser


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

fun List<PhotoDomain>.toPres() = map { domain -> domain.toPres() }

fun PhotoDomain.toPres() = PresPhoto(
    id = id,
    createdAt = createdAt,
    description = description,
    urls = urls.toPres(),
    user = user.toPres(),
    localPath = localPath,
    isFavourite = isFavourite
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
