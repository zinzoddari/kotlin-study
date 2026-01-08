package com.kotlin.check_point.repository

import com.kotlin.check_point.domain.Attendance
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import java.time.LocalDate
import kotlin.test.Test

class AttendanceMapRepositoryTest {

    private val repository: AttendancePersistence = AttendanceMapRepository()

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("회원 데이터가 존재하는지 확인할 때,")
    inner class exists {

        @BeforeAll
        fun beforeAll() {
            repository.save(Attendance(2L, LocalDate.of(2025, 1, 7), 1))
        }

        @Test
        @DisplayName("ID에 데이터가 없으면 false를 반환합니다.")
        fun isNotExistsUserId() {
            //given
            val userId: Long = 1L
            val date: LocalDate = LocalDate.now()

            //when
            val result: Boolean = repository.exists(userId, date)

            //then
            assertThat(result).isFalse
        }

        @Test
        @DisplayName("ID에 특정 날짜 데이터가 없으면 false를 반환합니다.")
        fun isNotExistsDate() {
            //given
            val userId: Long = 2L
            val date: LocalDate = LocalDate.now()

            //when
            val result: Boolean = repository.exists(userId, date)

            //then
            assertThat(result).isFalse
        }

        @Test
        @DisplayName("userId 특정 date의 값이 있다면 true를 반환합니다.")
        fun success() {
            //given
            val userId: Long = 2L;
            val date: LocalDate = LocalDate.of(2025, 1, 7)

            //when
            val result: Boolean = repository.exists(userId, date)

            //then
            assertThat(result).isTrue
        }
    }

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

    @Test
    @DisplayName("특정 회원의 정보를 전체 다 조회합니다.")
    fun findAllByUserID() {
        //given
        val userId: Long = 2L;

        repository.save(Attendance(userId, LocalDate.of(2025, 1, 7), 1))
        repository.save(Attendance(userId, LocalDate.of(2025, 1, 8), 1))

        //when
        val result: Map<LocalDate, Int> = repository.findByAllUserId(userId)

        //then
        assertThat(result).size().isEqualTo(2)
    }
}
