package com.kotlin.calculator

data class Item(
    val name: String,
    val price: Int,
    val quantity: Int
) {
    fun getTotal(): Int {
        return price * quantity
    }
}
