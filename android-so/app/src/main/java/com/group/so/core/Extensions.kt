package com.group.so.core

import java.text.NumberFormat
import java.util.Locale

const val ZERO = 0
const val HUNDRED = 100

fun Int?.orZero() = this ?: ZERO
fun Double?.orZero() = this ?: ZERO.toDouble()
fun String?.orZero() = this ?: "0"

fun Int?.isNegative() = this.orZero() < ZERO

fun Int?.isPositive() = this.orZero() > ZERO

fun Double?.isNegative() = this.orZero() < ZERO.toDouble()

fun Double?.isPositive() = this.orZero() > ZERO.toDouble()

fun Double?.toReal(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}
