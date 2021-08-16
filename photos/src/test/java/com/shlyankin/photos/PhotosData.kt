package com.shlyankin.photos

import com.shlyankin.myapplication.database.model.PhotoUrls
import com.shlyankin.myapplication.database.model.User
import com.shlyankin.photos.model.UiPhoto
import java.util.*

internal val uiPhoto1 = UiPhoto(
    "1",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)

internal val uiPhoto2 = UiPhoto(
    "2",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)

internal val uiPhoto3 = UiPhoto(
    "3",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)

internal val uiPhoto4 = UiPhoto(
    "4",
    Date(),
    "Hello, world",
    PhotoUrls("url", "url", "url", "url", "url"),
    User("Ivan", null),
    localPath = null,
    isFavourite = false
)