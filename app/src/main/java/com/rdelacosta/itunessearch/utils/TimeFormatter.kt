package com.rdelacosta.itunessearch.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TimeFormatter {

    companion object {

        fun toMinuteSecondFormat(ms: Long) : String {
            return String.format(
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(ms) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(ms) % TimeUnit.MINUTES.toSeconds(1)
            )
        }

        fun toDateFormat(data: String) : String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("MMMM dd, YYYY")
            val date: Date = inputFormat.parse(data)
            return outputFormat.format(date)
        }
    }
}