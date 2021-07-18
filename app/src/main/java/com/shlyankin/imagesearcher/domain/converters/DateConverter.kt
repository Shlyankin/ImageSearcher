package com.shlyankin.imagesearcher.domain.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun from(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun to(time: Long): Date {
        return Date(time)
    }

}