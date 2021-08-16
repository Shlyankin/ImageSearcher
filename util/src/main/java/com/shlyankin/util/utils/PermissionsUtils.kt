package com.shlyankin.util.utils

import android.content.Context
import android.content.pm.PackageManager

fun Context.getNonGrantedPermissions(vararg permissions: String): Array<String> {
    return permissions.filter { permission ->
        !checkPermission(permission)
    }.toTypedArray()
}

fun Context.checkPermission(vararg permissions: String): Boolean {
    return permissions.all { permission ->
        checkPermission(permission)
    }
}

fun Context.checkPermission(permission: String): Boolean {
    return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}