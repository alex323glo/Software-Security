package com.alex323glo.kpi.security.lab1

import com.alex323glo.kpi.security.lab1.data.Bit
import com.alex323glo.kpi.security.lab1.data.Bit.UNIT
import com.alex323glo.kpi.security.lab1.data.Bit.ZERO
import com.alex323glo.kpi.security.lab1.data.collectBitsToInt
import com.alex323glo.kpi.security.lab1.data.nextBit
import java.util.*

class TableGenerator(
        initialListOfLFSR: List<LinearFeedbackShiftRegister>
): PseudoRandomNumberGenerator<Bit> {

    val listOfLFSR: List<LinearFeedbackShiftRegister>
    val numberOfLFSR: Int
    val tableSize: Int
    val table: List<Bit>

    init {
        listOfLFSR = initialListOfLFSR.sortedBy { it.density }
        numberOfLFSR = listOfLFSR.size
        tableSize = Math.pow(2.0, numberOfLFSR.toDouble()).toInt()
        table = ArrayList(tableSize)
        fillTable(table, tableSize)
    }

    constructor(initializer: () -> List<LinearFeedbackShiftRegister>) : this(initializer())

    private fun fillTable(targetTable: MutableList<Bit>, targetTableCapacity: Int) {
        val random = Random(Random().nextLong())
        var numberOfZeros = 0
        var tempElement: Bit
        for (i in 0 until targetTableCapacity) {
            if (numberOfZeros >= targetTable.size / 2) {
                targetTable.add(UNIT)
                continue
            }

            if (random.nextBit() == ZERO) {
                targetTable.add(ZERO)
                numberOfZeros++
            } else {
                targetTable.add(UNIT)
            }
        }
    }

    override fun next(): Bit {
        val addressBitList = listOfLFSR.map { it.next() }.asReversed()
        val address = addressBitList.collectBitsToInt()
        return table[address]
    }

    fun getSnapshot(): String {
        val builder = StringBuilder()
        builder.append("{").append("\n")
        builder.append("\t\"table\": $table,").append("\n")
        builder.append("\t\"LFSRs\": [").append("\n")
        listOfLFSR.forEach { builder.append("\t\t$it,").append("\n") }
        builder.append("\t],").append("\n")
        builder.append("}").append("\n")
        return builder.toString()
    }
}