package com.kotlin.calculator

class Order {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val items: List<Item> = listOf(
                Item("아메리카노", 4500, 2),
                Item("샌드위치", 7000, 1),
                Item("케이크", 6000, 1)
            )

            OrderCalculator().printResult(items)
        }
    }
}