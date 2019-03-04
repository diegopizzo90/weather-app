package com.example.weatherapp.business.db.converter

import androidx.room.TypeConverter
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class TimestampConverter {

    companion object {

        private val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneId.of("UTC"))

        @TypeConverter
        @JvmStatic
        fun toZonedDateTime(value: String?): ZonedDateTime? {
            return value?.let {
                return formatter.parse(value, ZonedDateTime::from)
            }
        }

        @TypeConverter
        @JvmStatic
        fun fromZonedDateTime(date: ZonedDateTime?): String? {
            return date?.format(formatter)
        }
    }
}