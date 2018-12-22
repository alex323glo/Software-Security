package com.alex323glo.kpi.security.lab2.data

import kotlin.math.pow

enum class Bit (val value: Int) {
    ZERO(0),
    UNIT(1);

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JvmStatic
        fun Int.toBit(): Bit = when (this) {
            0 -> ZERO
            1 -> UNIT
            else -> throw IllegalArgumentException("Can't convert $this to single bit.")
        }

        fun Int.toBits(density: Int): Array<Bit> {
            var buffer = this
            val result = ArrayList<Bit>()

            while (buffer > 0) {
                result.add((buffer % 2).toBit())
                buffer /= 2
            }

            return when {
                result.size == density  -> result
                result.size < density   -> result.apply {
                    for (i in 0 until density - result.size) {
                        result.add(ZERO)
                    }
                }
                else -> {
                    throw IllegalArgumentException("Can't convert. Wrong density was entered. (must be >= ${result.size}")
                }
            }.toArray(Array(density) { ZERO })
        }

        @JvmStatic
        fun Array<Bit>.toInt(): Int = this.asSequence().foldIndexed(0) { index: Int, buffer: Int, bit: Bit ->
            buffer + bit.value * (2 pow index)
        }

        @JvmStatic
        private infix fun Int.pow(n: Int): Int = this.toDouble().pow(n).toInt()
    }
}
