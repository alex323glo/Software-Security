package com.alex323glo.kpi.security.lab2.logic

import com.alex323glo.kpi.security.lab2.data.SBlock
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UpdaterKtTest {

    @Test
    fun testUpdateBlock() {
        val initialCorrectSBlock = SBlock(
                3,
                4, 7, 2, 6, 1, 5, 0, 3
        )

        val actualResult = updateSBlock(initialCorrectSBlock, initialCorrectSBlock.density + 1)

        assertEquals(4, actualResult.density)
        assertTrue(verifySBlock(actualResult))
    }
}