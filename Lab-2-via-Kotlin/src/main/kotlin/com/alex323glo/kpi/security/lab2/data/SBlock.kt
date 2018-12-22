package com.alex323glo.kpi.security.lab2.data

import com.alex323glo.kpi.security.lab2.data.Bit.*
import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toBits
import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toInt
import com.alex323glo.kpi.security.lab2.util.generateXVars

class SBlock (val functions: List<Function>) {
    val density: Int = functions.size
    val numbers = generateNumbers()

    constructor(density: Int, vararg digits: Int) : this(
            digits.let {
                val result = ArrayList<Array<Bit>>()
                for (i in 0 until density) {
                    result.add(Array(digits.size) { ZERO })
                }
                it.forEachIndexed { j, digit ->
                    digit.toBits(density).forEachIndexed { i, bit ->
                        result[i][j] = bit
                    }
                }
                result.map { array ->  Function(array) }
            }
    )

    operator fun invoke(vectorX: Array<Bit>): Array<Bit> {
        if (vectorX.size != density) {
            throw IllegalArgumentException("Can't use SBlock (density=$density) with vector (size=${vectorX.size}).")
        }

        val result = Array(density) { ZERO }
        functions.forEachIndexed { index, function ->
            result[index] = function(vectorX)
        }

        return result
    }

    operator fun invoke(digit: Int): Int = this.invoke(digit.toBits(this.density)).toInt()

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{\n\r")
        functions.forEachIndexed { index, function ->
            builder.append("\t").append("$index: ").append(function).append(";\n\r")
        }
        builder.append("}")
        return builder.toString()
    }

    operator fun plus(sBlock: SBlock): SBlock = if (this.density == sBlock.density) {
        SBlock(this.density + 1, *(this.numbers + sBlock.numbers).toIntArray())
    } else {
        throw IllegalArgumentException("Can't sum SBlocks with different densities (${this.density} and ${sBlock.density}).")
    }

    private fun generateNumbers(): Array<Int> = generateXVars(density)
            .map {  x -> invoke(x) }
            .toTypedArray()
}
