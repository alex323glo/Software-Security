package com.alex323glo.kpi.security.lab1.data

import com.alex323glo.kpi.security.lab1.exception.BitException
import java.util.*

fun of(intBit: Int): Bit = when(intBit) {
    0 -> Bit.ZERO
    1 -> Bit.UNIT
    else -> throw BitException("Bit conversion exception can't convert $intBit to Bit (not equal to 0 or 1).")
}

fun bitsOf(vararg intBitVararg: Int): List<Bit> = intBitVararg
        .mapTo(LinkedList()) { of(it) }

fun bitsOf(bitList: List<Int>): List<Bit> = bitList
        .mapTo(LinkedList()) { of(it) }

fun bitsToNumber(bitList: List<Bit>): Int {
    var result = 0
    for (i in 0 until bitList.size) {
        if (bitList[i] == Bit.UNIT) {
            result += Math.pow(2.0, (bitList.size - i - 1).toDouble()).toInt()
        }
    }
    return result
}

fun Random.nextBit(): Bit = if (this.nextBoolean()) Bit.UNIT else Bit.ZERO

fun List<Bit>.collectBitsToInt(): Int = bitsToNumber(this)