package com.jslps.birsabasistha.utils
import androidx.room.TypeConverter
import java.util.*

 class Converter {
    // Set timezone value as GMT  to make time as reasonable


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}