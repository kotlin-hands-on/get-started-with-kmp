package com.jetbrains.greeting.greetingkmp

import kotlinx.datetime.*
import kotlin.time.Clock

fun daysUntilNewYear(): Int {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val closestNewYear = LocalDate(today.year + 1, 1, 1)
    return today.daysUntil(closestNewYear)
}

fun daysPhrase(): String = "There are only ${daysUntilNewYear()} days left until New Year! 🎆"