package com.kotlin.calculator

class OrderCalculator {

    fun calculateTotal(items: List<Item>): Int {
        return items.sumOf { it.price * it.quantity }
    }

    fun calculateDiscount(total: Int): Int {
        return when {
            total >= 50000 -> total * 10 / 100
            total >= 30000 -> total * 5 / 100
            else -> 0
        }
    }

    fun printResult(items: List<Item>) {
        val total = calculateTotal(items)
        val discount = calculateDiscount(total)
        val answer = total - discount

        println("총 주문 금액: ${total}원")
        println("할인 금액: ${discount}원")
        println("최종 결제 금액: ${answer}원")
    }
}
