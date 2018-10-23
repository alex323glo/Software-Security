package com.alex323glo.kpi.security

import com.alex323glo.kpi.security.bit.Bit
import java.util.*

data class Table (
        val bitDepth: Int,
        val numberOfLFSRs: Int
) {

    val innerTable: Array<Bit>

    init {
        innerTable = Array(bitDepth) { Bit.ZERO }

        var zeroCounter = 0
        var unitCounter = 0

        for (i in 0 until innerTable.size) {
            val localRandom = Random(superRandom.nextLong())
            innerTable[i] = Bit.of(localRandom.nextBoolean())

            if (innerTable[i] == Bit.ZERO) {
                zeroCounter++
            } else {
                unitCounter++
            }

            if (zeroCounter >= innerTable.size / 2) {
                for (j in i until innerTable.size) {
                    innerTable[j] = Bit.UNIT
                }
                break
            }

            if (unitCounter >= innerTable.size / 2) {
                for (j in i until innerTable.size) {
                    innerTable[j] = Bit.ZERO
                }
                break
            }
        }

    }


    fun generate(listOfLFSRs: List<LFSR>): Bit {
        var result = Bit.ZERO
        var position = 0

        val number = Array(numberOfLFSRs) { Bit.ZERO }

        for (i in 0 until number.size) {
            number[i] = listOfLFSRs[i].generateStep()

            if (number[i] == Bit.UNIT) {
                position += (Math.pow(2.0, i.toDouble()).toInt())
            }
        }

        return innerTable[position]
    }


    companion object {
        @JvmStatic val superRandom = Random()
    }

}