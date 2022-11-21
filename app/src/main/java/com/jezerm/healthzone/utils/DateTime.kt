package com.jezerm.healthzone.utils

import android.content.Context
import android.icu.text.RelativeDateTimeFormatter
import android.text.format.DateUtils
import com.jezerm.healthzone.MainActivity
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDateTime
import java.time.MonthDay
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit

class DateTime {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV")

        /**
         * Format must be of type "yyyy-MM-dd HH:mm VV", such as:
         *
         * 2022-05-10 16:45 -06:00
         *
         * 2022-05-30 09:12 America/Managua
         */
        fun format(text: CharSequence): ZonedDateTime {
            return ZonedDateTime.parse(text, formatter)
        }

        fun format(year: Int, month: Int, day: Int, hour: Int, minute: Int, zoneId: ZoneId): ZonedDateTime {
            return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, zoneId)
        }

        fun toString(dateTime: ZonedDateTime) {
            val now = ZonedDateTime.now()
            val localDateTime = dateTime.withZoneSameInstant(ZoneId.systemDefault())

            val elapsed = localDateTime.toEpochSecond() * 1000 + now.get(ChronoField.MILLI_OF_SECOND)

            val c = MainActivity.appContext
            val formatted = DateUtils.getRelativeDateTimeString(c, elapsed, DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0)
            println("FORMATTED: $formatted")
        }
    }
}