package com.alex323glo.kpi.security.lab2.data

import com.alex323glo.kpi.security.lab2.data.Bit.UNIT
import com.alex323glo.kpi.security.lab2.data.Bit.ZERO
import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toInt
import com.alex323glo.kpi.security.lab2.data.Bit.Companion.toBits
import org.junit.Assert.assertArrayEquals

import org.junit.Assert.assertEquals
import org.junit.Test

class BitTest {

    @Test
    fun toInt() {
        val testArray = Array(4) { ZERO }
                .apply {
                    this[3] = UNIT
                    this[2] = ZERO
                    this[1] = UNIT
                    this[0] = UNIT
                }

        val intResult = testArray.toInt()

        assertEquals(11, intResult)
    }

    @Test
    fun toBits() {
        val testInt1 = 11
        val testInt2 = 3

        val expectedArray1 = Array(4) { ZERO }
                .apply {            // (11)
                    this[3] = UNIT  // 1
                    this[2] = ZERO  // 0
                    this[1] = UNIT  // 1
                    this[0] = UNIT  // 1
                }
        val expectedArray2 = Array(4) { ZERO }
                .apply {            // (3)
                    this[3] = ZERO  // 0
                    this[2] = ZERO  // 0
                    this[1] = UNIT  // 1
                    this[0] = UNIT  // 1
                }

        val actualArray1 = testInt1.toBits(4)
        val actualArray2 = testInt2.toBits(4)

        assertArrayEquals(expectedArray1, actualArray1)
        assertArrayEquals(expectedArray2, actualArray2)
    }
}