package com.kotlin.check_point.repository

import com.kotlin.check_point.domain.Attendance
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.time.LocalDate
import kotlin.test.Test

class AttendanceMapRepositoryTest {

    private val repository: AttendancePersistence = AttendanceMapRepository()

    @Nested
    @DisplayName("저장을 할 때,")
    inner class save {

        @Test
        @DisplayName("성공적으로 저장합니다.")
        fun success() {
            //given
            val userId: Long = 1L
            val date: LocalDate = LocalDate.of(2026, 1, 7)
            val point: Int = 1
            val input: Attendance = Attendance(userId, date, point)

            //when & then
            assertDoesNotThrow {
                repository.save(input)
            }
        }
    }
}
