package com.alex323glo.kpi.security.lab1

import com.alex323glo.kpi.security.lab1.data.Bit
import com.alex323glo.kpi.security.lab1.data.bitsOf
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LinearFeedbackShiftRegisterTest {

    private lateinit var lfsr: LinearFeedbackShiftRegister
    private val startRegister = bitsOf(1, 0, 1, 0)
    private val feedbackFunction = bitsOf(1, 0, 0)

    val expectedNextBitList: List<Bit> = bitsOf(1, 0, 1, 0)
    val expectedStateList: List<List<Bit>> = listOf(
            bitsOf(0, 1, 0, 1),
            bitsOf(1, 0, 1, 1),
            bitsOf(0, 1, 1, 1),
            bitsOf(1, 1, 1, 1)
    )

    @Before
    fun setUp() {
        lfsr = LinearFeedbackShiftRegister(feedbackFunction, startRegister)
    }

    @Test
    operator fun next() {
        val actualNextBitList = ArrayList<Bit>()
        val actualStateList = ArrayList<List<Bit>>()

        for (i in 1..4) {
            actualNextBitList.add(lfsr.next())
            actualStateList.add(ArrayList(lfsr.register))
        }

        assertEquals(expectedNextBitList, actualNextBitList)
        assertEquals(expectedStateList, actualStateList)
    }
}