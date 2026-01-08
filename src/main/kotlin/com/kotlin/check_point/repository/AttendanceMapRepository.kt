package com.kotlin.check_point.repository

import com.kotlin.check_point.domain.Attendance
import java.time.LocalDate

class AttendanceMapRepository : AttendancePersistence {

    private val repository: MutableMap<Long, MutableMap<LocalDate, Int>> = mutableMapOf()

    override fun exists(userId: Long, date: LocalDate): Boolean {
        if (!repository.containsKey(userId)) {
            return false
        }

        val userRepository: MutableMap<LocalDate, Int> = repository.getValue(userId)

        if (userRepository.isEmpty()) {
            return false
        }

        return userRepository.containsKey(date)
    }

    override fun save(attendance: Attendance) {
        val userRepository: MutableMap<LocalDate, Int> = repository.getOrDefault(attendance.userId, mutableMapOf())

        userRepository[attendance.date] = attendance.point

        repository[attendance.userId] = userRepository
    }

    override fun findByAllUserId(userId: Long): MutableMap<LocalDate, Int> {
        return repository.getValue(userId)
    }
}