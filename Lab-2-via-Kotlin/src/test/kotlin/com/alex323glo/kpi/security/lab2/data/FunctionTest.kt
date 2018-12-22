package com.alex323glo.kpi.security.lab2.data

import org.junit.Test

import org.junit.Assert.*

import com.alex323glo.kpi.security.lab2.data.Bit.ZERO
import com.alex323glo.kpi.security.lab2.data.Bit.UNIT

class FunctionTest {

    @Test
    operator fun invoke() {
        val initialVector = arrayOf(
                UNIT,
                UNIT,
                ZERO,
                UNIT
        )
        val function = Function(initialVector)

        assertEquals(1, function(0))
        assertEquals(1, function(1))
        assertEquals(0, function(2))
        assertEquals(1, function(3))
    }
}