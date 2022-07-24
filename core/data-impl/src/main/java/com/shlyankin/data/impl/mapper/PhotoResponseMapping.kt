package com.shlyankin.data.impl.mapper

import com.shlyankin.db.model.FavouritePhotoEntity
import com.shlyankin.db.model.PhotoUrlsEntity
import com.shlyankin.db.model.ProfileImageEntity
import com.shlyankin.db.model.UserEntity
import com.shlyankin.domain.models.PhotoDomain
import com.shlyankin.domain.models.PhotoUrlsDomain
import com.shlyankin.domain.models.ProfileImageDomain
import com.shlyankin.domain.models.UserDomain
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.net.model.PhotoUrls
import com.shlyankin.net.model.ProfileImage
import com.shlyankin.net.model.User

// Photo

fun PhotoResponse.toEntity(): FavouritePhotoEntity = FavouritePhotoEntity(
    id, createdAt, description, urls.toEntity(), user.toEntity(), ""
)

fun PhotoResponse.toDomain(): PhotoDomain = PhotoDomain(
    id, createdAt, description, urls.toDomain(), user.toDomain()
)

fun FavouritePhotoEntity.toDomain() = PhotoDomain(
    id, createdAt, description, urls.toDomain(), user.toDomain(), isFavourite = true, localPath
)

fun PhotoDomain.toEntity(localPath: String): FavouritePhotoEntity = FavouritePhotoEntity(
    id, createdAt, description, urls.toEntity(), user.toEntity(), localPath
)

// Photo urls

fun PhotoUrls.toEntity(): PhotoUrlsEntity = PhotoUrlsEntity(
    raw = raw, full = full, regular = regular, small = small, thumb = thumb
)

fun PhotoUrlsEntity.toDomain(): PhotoUrlsDomain = PhotoUrlsDomain(
    raw = raw, full = full, regular = regular, small = small, thumb = thumb
)

fun PhotoUrlsDomain.toEntity(): PhotoUrlsEntity = PhotoUrlsEntity(
    raw = raw, full = full, regular = regular, small = small, thumb = thumb
)

fun PhotoUrls.toDomain(): PhotoUrlsDomain = PhotoUrlsDomain(
    raw = raw, full = full, regular = regular, small = small, thumb = thumb
)

// User

fun User.toEntity(): UserEntity = UserEntity(
    name = name, profileImage = profileImage?.toEntity()
)

fun UserEntity.toDomain(): UserDomain = UserDomain(
    name = name, profileImage = profileImage?.toDomain()
)

fun UserDomain.toEntity(): UserEntity = UserEntity(
    name = name, profileImage = profileImage?.toEntity()
)

fun User.toDomain(): UserDomain = UserDomain(
    name = name, profileImage = profileImage?.toDomain()
)

// Profile Image

fun ProfileImage.toEntity() = ProfileImageEntity(
    small = small, medium = medium, large = large
)

fun ProfileImageEntity.toDomain() = ProfileImageDomain(
    small = small, medium = medium, large = large
)

fun ProfileImageDomain.toEntity() = ProfileImageEntity(
    small = small, medium = medium, large = large
)

fun ProfileImage.toDomain() = ProfileImageDomain(
    small = small, medium = medium, large = large
)