package com.kotlin.check_point.service

import com.kotlin.check_point.domain.Attendance
import com.kotlin.check_point.repository.AttendanceMapRepository
import com.kotlin.check_point.repository.AttendancePersistence
import java.time.LocalDate

class AttendanceService(
    val repository: AttendancePersistence = AttendanceMapRepository()
) {

    fun checkIn(userId: Long, date: LocalDate) {
        // 중복 출석 리젝
        if (repository.exists(userId, date)) {
            return
        }

        // 연속 출석 보너스 체크
        var index = 0L
        for (i in index..7) {
            index++

            val targetDate: LocalDate = date.minusDays(i)

            if (!repository.exists(userId, targetDate)) {
                return
            }
        }

        val point = when {
            index < 3 -> 10
            index < 7 -> 20
            else -> 50
        }

        // 출석 저장
        repository.save(Attendance(userId, date, point))
    }

    fun getPoint(userId: Long): Int {
        // 1. 있는지 확인
        val map: Map<LocalDate, Int> = repository.findByAllUserId(userId)

        if (map.isEmpty()) {
            throw RuntimeException()
        }

        // 2. 결과 sum 값 추출
        return map.values.sum()
    }

    fun getHistory(userId: Long): List<LocalDate> {
        // 1. 있는지 확인
        val map: Map<LocalDate, Int> = repository.findByAllUserId(userId)

        return map.keys.toList()
    }

    fun leaderboard(topN: Int) {
        val map: Map<LocalDate, Int> = mapOf()

        return map.entries.stream()
            .map {  }
    }
}