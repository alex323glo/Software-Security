package com.alex323glo.kpi.security.lab2.data

import com.alex323glo.kpi.security.lab2.data.Bit.UNIT
import com.alex323glo.kpi.security.lab2.data.Bit.ZERO
import org.junit.Test

import org.junit.Assert.*

class SBlockTest {

    private val initialFunctions = listOf(
            Function(1, 0, 0, 1),
            Function(1, 1, 0, 0)
    )
    val sBlock = SBlock(initialFunctions)

    @Test
    fun invokeBits() {
        val actual1 = sBlock.invoke(arrayOf(ZERO, ZERO))
        val actual2 = sBlock.invoke(arrayOf(UNIT, ZERO))
        val actual3 = sBlock.invoke(arrayOf(ZERO, UNIT))
        val actual4 = sBlock.invoke(arrayOf(UNIT, UNIT))

        assertArrayEquals(arrayOf(UNIT, UNIT), actual1)
        assertArrayEquals(arrayOf(ZERO, UNIT), actual2)
        assertArrayEquals(arrayOf(ZERO, ZERO), actual3)
        assertArrayEquals(arrayOf(UNIT, ZERO), actual4)
    }

    @Test
    fun invokeDigit() {
        assertEquals(3, sBlock(0))
        assertEquals(2, sBlock(1))
        assertEquals(0, sBlock(2))
        assertEquals(1, sBlock(3))
    }
}