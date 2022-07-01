package com.shlyankin.db.converter

import androidx.room.TypeConverter
import java.util.*

internal class DateConverter {

    @TypeConverter
    fun from(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun to(time: Long): Date {
        return Date(time)
    }

}