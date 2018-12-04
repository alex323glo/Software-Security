package com.alex323glo.kpi.security.lab1

import com.alex323glo.kpi.security.lab1.data.Bit
import com.alex323glo.kpi.security.lab1.data.Bit.*
import com.alex323glo.kpi.security.lab1.data.bitsOf
import com.alex323glo.kpi.security.lab1.data.nextBit
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class FunctionalTest {

    private lateinit var selection: List<Bit>
    private lateinit var polynoms: List<List<Bit>>
    private lateinit var initialStates: List<List<Bit>>
    private lateinit var listOfLFSR: List<LinearFeedbackShiftRegister>
    private lateinit var tableGenerator: TableGenerator

    @Before
    fun setUp() {
        selection = ArrayList(SELECTION_SIZE)
        polynoms = listOf(
                bitsOf(0, 0, 0, 0, 1),      // 6
                bitsOf(0, 0, 0, 1, 0, 0),   // 7
                bitsOf(0, 0, 0, 1, 1, 1, 0),    // 8
                bitsOf(0, 0, 0, 0, 1, 0, 0, 0), // 9
                bitsOf(0, 0, 0, 0, 0, 0, 1, 0, 0),      // 10
                bitsOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 0),   // 11
                bitsOf(0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1),    // 12
                bitsOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1), // 13
                bitsOf(0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1),      // 14
                bitsOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),   // 15
                bitsOf(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1),    // 16
                bitsOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0)  // 17
        )
        initialStates = listOf(
                randomizeBitList(6),
                randomizeBitList(7),
                randomizeBitList(8),
                randomizeBitList(9),
                randomizeBitList(10),
                randomizeBitList(11),
                randomizeBitList(12),
                randomizeBitList(13),
                randomizeBitList(14),
                randomizeBitList(15),
                randomizeBitList(16),
                randomizeBitList(17)
        )

        listOfLFSR = listOf(
                getLFSR(polynoms[0], initialStates[0]),
                getLFSR(polynoms[1], initialStates[1]),
                getLFSR(polynoms[2], initialStates[2]),
                getLFSR(polynoms[3], initialStates[3]),
                getLFSR(polynoms[4], initialStates[4]),
                getLFSR(polynoms[5], initialStates[5]),
                getLFSR(polynoms[6], initialStates[6]),
                getLFSR(polynoms[7], initialStates[7]),
                getLFSR(polynoms[8], initialStates[8]),
                getLFSR(polynoms[9], initialStates[9]),
                getLFSR(polynoms[10], initialStates[10]),
                getLFSR(polynoms[11], initialStates[11])
        )

        tableGenerator = TableGenerator(listOfLFSR)
    }

    private fun randomizeBitList(size: Int): List<Bit> {
        val random = Random(Random().nextLong())
        val result = LinkedList<Bit>()
        var unitsCounter = 0
        var buffer: Bit
        for (i in 0 until size) {
            buffer = random.nextBit()
            unitsCounter += buffer.value
            result.add(buffer)
        }

        return if (unitsCounter > 0) {
            result
        } else {
            randomizeBitList(size)
        }
    }

    private fun generateSelection(generator: PseudoRandomNumberGenerator<Bit>, size: Int): List<Bit> =
            List(size) { generator.next() }

    @Test
    fun runAll() {
        runFrequencyTest()
        runDifferentialTest()
        runRankTest()
        runLinearComplexityTest()
    }

    private fun runFrequencyTest() {
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        println("<> <> Frequency test <> <>")
        println("Selection size: $SELECTION_SIZE")
        selection = generateSelection(tableGenerator, SELECTION_SIZE)
        val result = frequencyTest(selection)
        println("Result: $result")
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    private fun runDifferentialTest() {
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        println("<> <> Differential test <> <>")
        println("Selection size: $SELECTION_SIZE")
        selection = generateSelection(tableGenerator, SELECTION_SIZE)
        val result = differentialTest(selection)
        println("Result: $result")
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    private fun runRankTest() {
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        println("<> <> Rank test <> <>")
        println("Selection size: $SELECTION_SIZE")
        selection = generateSelection(tableGenerator, SELECTION_SIZE)
        val result3 = rankTest(selection, 3)
        val result4 = rankTest(selection, 4)
        val result5 = rankTest(selection, 5)
        val result6 = rankTest(selection, 6)
        println("Result (3): ${Arrays.toString(result3)}")
        println("Result (4): ${Arrays.toString(result4)}")
        println("Result (5): ${Arrays.toString(result5)}")
        println("Result (6): ${Arrays.toString(result6)}")
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    private fun runLinearComplexityTest() {
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        println("<> <> Complexity test <> <>")
        println("Selection size: $SELECTION_SIZE")
        selection = generateSelection(tableGenerator, SELECTION_SIZE)
        val result = linearComplexityTest(selection)
        println("Result: $result")
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    private fun frequencyTest(bits: List<Bit>): Double {
        var buffer = 0
        bits.forEach {
            if (it == UNIT) {
                buffer++
            }
        }
        return buffer.toDouble() / bits.size
    }

    private fun differentialTest(bits: List<Bit>): Double {
        var buffer = 0
        for (i in 1 until bits.size) {
            if (bits[i - 1] % bits[i] == UNIT) {
                buffer++
            }
        }
        return buffer.toDouble() / (bits.size - 1)
    }

    private fun rankTest(bits: List<Bit>, width: Int): IntArray {
        if (width <= 0 || width > 10) {
            throw IllegalArgumentException("Wrong width was passed! Must be in range [2;10] (but was $width)!")
        }

        val result = IntArray(Math.pow(2.0, width.toDouble()).toInt())
        var buffer = 0
        for (i in 0 until bits.size - width) {
            for (j in 0 until width) {
                buffer = buffer shl 1
                buffer = buffer or bits[i + j].value
            }
            result[buffer]++
            buffer = 0
        }
        return result
    }

    private fun linearComplexityTest(bits: List<Bit>): Int {
        val n = bits.size
        val c = Array(n) { ZERO }
        var b = Array(n) { ZERO }
        var t: Array<Bit>
        b[0] = UNIT
        c[0] = UNIT
        var l = 0
        var m = -1
        for (i in 0 until n) {
            var d = 0
            for (j in 0 until l) {
                d %= c[j].value * bits[i - j].value
            }
            if (d == 1) {
                t = Arrays.copyOf(c, n)
                val iSubM = i - m
                for (k in 0 until (n - iSubM)) {
                    c[iSubM + k] %= b[k]
                }
                if (l <= i / 2) {
                    l = i + 1 - l
                    m = i
                    b = Arrays.copyOf(t, n)
                }
            }
        }
        return l
    }

    companion object {
        const val SELECTION_SIZE = 15_000
    }
}