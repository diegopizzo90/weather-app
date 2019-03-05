package com.example.weatherapp.business.db.converter

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

@RunWith(JUnit4::class)
class ZonedDateTimeConverterTest {

    @Test
    fun toZonedDateTimeTest() {
        val zoneDateTime =
            ZonedDateTimeConverter.toZonedDateTime("2019-03-04T10:39:37.640Z[UTC]")//dd/MM/yyyy HH:mm-> 04/03/2019 10:39
        assertEquals(zoneDateTime!!.dayOfMonth, 4)
        assertEquals(zoneDateTime.month.value, 3)
        assertEquals(zoneDateTime.year, 2019)
        assertEquals(zoneDateTime.hour, 10)
        assertEquals(zoneDateTime.minute, 39)
        assertEquals(zoneDateTime.second, 37)
        assertEquals(zoneDateTime.zone, ZoneId.of("UTC"))
    }

    @Test
    fun fromZonedDateTime() {
        val zoneDateTimeString =
            ZonedDateTimeConverter.fromZonedDateTime(ZonedDateTime.of(1990, 1, 17, 12, 13, 14, 0, ZoneId.of("UTC")))
        assertEquals(zoneDateTimeString, "1990-01-17T12:13:14Z[UTC]")
    }
}