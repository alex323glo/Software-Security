package com.alex323glo.kpi.security.lab2.logic

import org.junit.Test

class GeneratorKtTest {

    @Test
    fun testGenerateSBlock() {
        val sBlock = generateSBlock(3)
        println(sBlock)
    }
}