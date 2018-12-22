package com.alex323glo.kpi.security.lab2.util

import com.alex323glo.kpi.security.lab2.data.Bit
import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toBit
import kotlin.math.pow

fun generateXVars(density: Int): List<Int> = ArrayList<Int>().apply {
    for (i in 0 until 2.0.pow(density).toInt()) {
        this.add(i)
    }
}

infix fun Array<Bit>.xor(array: Array<Bit>): Array<Bit> = this
        .mapIndexed { index, thisBit ->
            (thisBit.value xor array[index].value).toBit()
        }
        .toTypedArray()