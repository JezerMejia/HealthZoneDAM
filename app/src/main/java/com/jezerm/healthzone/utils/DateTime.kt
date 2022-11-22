package com.jezerm.healthzone.utils

import android.icu.text.DisplayContext
import android.icu.text.NumberFormat
import android.icu.text.RelativeDateTimeFormatter
import android.icu.text.RelativeDateTimeFormatter.*
import android.icu.util.ULocale
import androidx.room.TypeConverter
import com.jezerm.healthzone.MainActivity
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

class DateTime {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV")

        fun now(): ZonedDateTime {
            return ZonedDateTime.now()
        }

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

        fun format(
            year: Int,
            month: Int,
            day: Int,
            hour: Int,
            minute: Int,
            zoneId: ZoneId
        ): ZonedDateTime {
            return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, zoneId)
        }

        fun toString(dateTime: ZonedDateTime): String {
            val now = ZonedDateTime.now()
            val localDateTime = dateTime.withZoneSameInstant(ZoneId.systemDefault())
            val relativeFormatter = RelativeDateTimeFormatter.getInstance(
                ULocale.getDefault(),
                NumberFormat.getInstance(),
                Style.LONG,
                DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE
            )

            val days = now.until(localDateTime, ChronoUnit.DAYS)
            val hours = now.until(localDateTime, ChronoUnit.HOURS) - days * 24
            val minutes = now.until(localDateTime, ChronoUnit.MINUTES) - (days * 24 + hours) * 60

            if (days > 1) {
                return formatter.format(localDateTime)
            } else if (days > 0) {
                return relativeFormatter.format(Direction.NEXT, AbsoluteUnit.DAY)
            } else if (days < 0) {
                return formatter.format(localDateTime)
            }

            if (hours >= 1) {
                return relativeFormatter.format(
                    hours.toDouble(),
                    Direction.NEXT,
                    RelativeUnit.HOURS
                )
            } else if (hours < 0) {
                return relativeFormatter.format(
                    abs(hours.toDouble()),
                    Direction.LAST,
                    RelativeUnit.HOURS
                )
            }
            if (minutes >= 1) {
                return relativeFormatter.format(
                    minutes.toDouble(),
                    Direction.NEXT,
                    RelativeUnit.MINUTES
                )
            } else if (minutes < 0) {
                return relativeFormatter.format(
                    abs(minutes.toDouble()),
                    Direction.LAST,
                    RelativeUnit.MINUTES
                )
            }
            return relativeFormatter.format(Direction.PLAIN, AbsoluteUnit.NOW)
        }

        @TypeConverter
        @JvmStatic
        fun fromDatetime(value: ZonedDateTime): String {
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toDatetime(value: String): ZonedDateTime {
            return try {
                format(value)
            } catch (e: Exception) {
                ZonedDateTime.now()
            }
        }
    }
}