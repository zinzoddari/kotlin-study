package com.kotlin.check_point.repository

import com.kotlin.check_point.domain.Attendance
import java.time.LocalDate

interface AttendancePersistence {

    fun exists(userId: Long, date: LocalDate): Boolean

    fun save(attendance: Attendance)

    fun findByAllUserId(userId: Long): MutableMap<LocalDate, Int>
}