package com.kotlin.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

class OrderCalculatorTest {

    @Test
    @DisplayName("입력 된 Item의 총 합을 구햅니다.")
    fun calculateTotal() {
        //given
        val expectedTotal = 13000
        val items: List<Item> = listOf(
            Item("김밥", 3000, 3),
            Item("볶음밥", 4000, 1)
        )

        //when
        val result = OrderCalculator().calculateTotal(items)

        //then
        assertThat(result).isEqualTo(expectedTotal)
    }

    @Nested
    @DisplayName("할인 가격을 구할 때,")
    inner class calculateDiscount {

        @ParameterizedTest
        @ValueSource(ints = [50000, 100000, 500000])
        @DisplayName("총 합이 5만원을 넘기면, 10% 할인 가격을 가져옵니다.")
        fun totalOver50000(total: Int) {
            //given
            val expectedResult: Int = (total * 0.1).toInt()

            //when
            val result: Int = OrderCalculator().calculateDiscount(total)

            //then
            assertThat(result).isEqualTo(expectedResult)
        }

        @ParameterizedTest
        @ValueSource(ints = [30000, 35000, 49999])
        @DisplayName("총 합이 3만원을 넘기면, 5% 할인 가격을 가져옵니다.")
        fun totalOver30000(total: Int) {
            //given
            val expectedResult: Int = (total * 0.05).toInt()

            //when
            val result: Int = OrderCalculator().calculateDiscount(total)

            //then
            assertThat(result).isEqualTo(expectedResult)
        }
    }
}
