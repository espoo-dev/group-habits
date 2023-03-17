@file:Suppress("MaxLineLength", "ImplicitDefaultLocale", "TooManyFunctions", "LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

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

fun Double?.toMoney(): String {
    return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
}

fun Double?.toFormat(): String {
    return String.format("%.2f", this)
}

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true
