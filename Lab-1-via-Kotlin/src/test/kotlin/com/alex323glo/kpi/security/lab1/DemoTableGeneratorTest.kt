package com.alex323glo.kpi.security.lab1

import com.alex323glo.kpi.security.lab1.data.Bit
import com.alex323glo.kpi.security.lab1.data.bitsOf
import org.junit.Before

import org.junit.Test

class DemoTableGeneratorTest {

    lateinit var generator: TableGenerator

    @Before
    fun setUp() {
        generator = TableGenerator {
            listOf( getLFSR(bitsOf(1, 1, 0, 0),         bitsOf(1, 0, 1, 1, 0)),
                    getLFSR(bitsOf(1, 0, 0, 1, 0),      bitsOf(1, 0, 1, 1, 1, 0)),
                    getLFSR(bitsOf(1, 0, 0, 0, 0, 1),   bitsOf(1, 0, 1, 1, 0, 1, 1)))
        }
    }

    @Test
    fun demo() {
        var temp: Bit
        for (i in 1..4) {
            println("========================================")
            println("Cycle: $i")

            println("Snapshot before:")
            println(generator.getSnapshot())

            temp = generator.next()
            println("_______________________________")

            println("Snapshot after:")
            println(generator.getSnapshot())

            println("Result: $temp")
            println("========================================")
        }
    }
}