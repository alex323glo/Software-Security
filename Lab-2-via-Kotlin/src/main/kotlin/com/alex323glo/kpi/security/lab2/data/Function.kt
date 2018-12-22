package com.alex323glo.kpi.security.lab2.data

import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toInt
import java.util.*
import kotlin.math.log2

import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toBits
import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toBit

class Function (val vector: Array<Bit>) {

    constructor(vararg bits: Int) : this(
            bits.map { it.toBit() }
                    .toTypedArray()
    )

    val density = log2(vector.size.toDouble()).toInt()

    operator fun invoke(vectorX: Array<Bit>): Bit = if (vectorX.size == density) {
        vector[vectorX.toInt()]
    } else {
        throw IllegalArgumentException("Can't use X Vector, which length != ${log2(density.toDouble()).toInt()}!")
    }

    operator fun invoke(digit: Int): Int = this.invoke(digit.toBits(this.density)).value

    operator fun plus(function: Function): Function = Function(this.vector + function.vector)

    override fun toString(): String {
        return Arrays.toString(vector)
    }
}