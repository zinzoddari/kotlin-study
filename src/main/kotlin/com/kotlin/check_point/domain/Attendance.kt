package com.kotlin.check_point.domain

import java.time.LocalDate

data class Attendance(
    val userId: Long,
    val date: LocalDate,
    val point: Int
)
