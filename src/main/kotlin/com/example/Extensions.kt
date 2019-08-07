package com.example

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.time.temporal.TemporalUnit


fun LocalDateTime.formatDateAgo(standardTime: LocalDateTime = LocalDateTime.now()): String {

    val timeUtil: (TemporalUnit) -> Long = {

        this.until(standardTime, it)

    }

    return when {

        timeUtil(ChronoUnit.SECONDS) < 5 -> "just now"
        timeUtil(ChronoUnit.SECONDS) < 60 -> "${timeUtil(ChronoUnit.SECONDS)} seconds ago"
        timeUtil(ChronoUnit.MINUTES) < 60 -> "${timeUtil(ChronoUnit.MINUTES)} minutes ago"
        timeUtil(ChronoUnit.HOURS) < 24 -> "${timeUtil(ChronoUnit.HOURS)} hours ago"
        timeUtil(ChronoUnit.DAYS) < standardTime.lastDayOfMonth() -> "${timeUtil(ChronoUnit.DAYS)} days ago"
        timeUtil(ChronoUnit.MONTHS) < 12 -> "${timeUtil(ChronoUnit.MONTHS)} months ago"
        else -> "${timeUtil(ChronoUnit.YEARS)} years ago"

    }

}

fun LocalDateTime.lastDayOfMonth() =
    this.with(TemporalAdjusters.lastDayOfMonth()).dayOfMonth
