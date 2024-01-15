package edu.pwr.jakubwiraszka.flickrgallery.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Utils {
    companion object {
        fun formatDate(isoDate: String): String {
            return LocalDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                .toString()
        }
    }
}