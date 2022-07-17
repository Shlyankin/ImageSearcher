package com.shlyankin.photos

import com.shlyankin.db.database.model.PhotoUrls
import com.shlyankin.db.database.model.User
import com.shlyankin.photos.model.PresPhoto
import java.util.*

internal val uiPhoto1 = PresPhoto(
    "1",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)

internal val uiPhoto2 = PresPhoto(
    "2",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)

internal val uiPhoto3 = PresPhoto(
    "3",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)

internal val uiPhoto4 = PresPhoto(
    "4",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)