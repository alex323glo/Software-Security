package com.alex323glo.kpi.security.lab2

import com.alex323glo.kpi.security.lab2.logic.generateSBlock
import com.alex323glo.kpi.security.lab2.logic.updateSBlock
import com.alex323glo.kpi.security.lab2.logic.verifySBlock

/**
 * If something goes wrong, try to use [4, 7, 2, 6, 1, 5, 0, 3] SBlock
 * instead of generating new one via generateSBlock() fun.
 */
fun main(args: Array<String>) {
    val initialDensity = 3
    val targetDensity = 6

    val initialSBlock = generateSBlock(initialDensity)
    val updatedSBlock = updateSBlock(initialSBlock, targetDensity)
    val isCorrect = verifySBlock(updatedSBlock)

    println("S-Block: \n$updatedSBlock \n\nIs valid: $isCorrect")
}

