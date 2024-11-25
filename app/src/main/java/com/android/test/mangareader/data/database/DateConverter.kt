package com.android.test.mangareader.data.database

import androidx.room.TypeConverter
import java.util.Date

object DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long): Date{
        return Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long?{
        return date?.time
    }
}