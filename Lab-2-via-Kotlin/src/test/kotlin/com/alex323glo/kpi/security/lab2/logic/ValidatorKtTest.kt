package com.alex323glo.kpi.security.lab2.logic

import com.alex323glo.kpi.security.lab2.data.SBlock
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.pow

class ValidatorKtTest {

    private val testSBlock = SBlock(3, 4, 7, 2, 6, 1, 5, 0, 3)

    @Test
    fun testVerifySBlock() {
        val result = verifySBlock(testSBlock)
        assertTrue(result)
        println("SBlock is ${if (result) "" else "not"} valid!")
    }

    @Test
    fun testEvaluateSBlockFunctions() {
        val result = evaluateSBlockFunctions(testSBlock)
        val resultEvaluation = result.asSequence()
                .flatMap { it.asSequence() }
                .all { it == 2.0.pow(testSBlock.density.toDouble() - 1.0).toInt() }
        assertTrue(resultEvaluation)
        println("SBlock stats:")
        result.forEachIndexed { i, functionResultsList ->
            print("f$i: ")
            functionResultsList.forEachIndexed { j, eResult ->
                print("$eResult, ")
            }
            println()
        }
    }
}