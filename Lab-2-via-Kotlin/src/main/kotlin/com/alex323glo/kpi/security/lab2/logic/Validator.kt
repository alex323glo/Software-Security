package com.alex323glo.kpi.security.lab2.logic

import com.alex323glo.kpi.security.lab2.data.Function
import com.alex323glo.kpi.security.lab2.data.SBlock
import kotlin.math.pow

fun verifySBlock(sBlock: SBlock): Boolean = sBlock.let{ block ->
    2.0.pow(block.density - 1).toInt()
}.let { targetValue ->
    return evaluateSBlockFunctions(sBlock)
            .asSequence()
            .flatMap { it.asSequence() }
            .all { it == targetValue }
}

fun evaluateSBlockFunctions(sBlock: SBlock): List<List<Int>> {
    val density = sBlock.density
    val numOfVariants = 2.0.pow(density.toDouble()).toInt()
    val functions = sBlock.functions

    val xVars = generateXVars(numOfVariants)
    val eVars = generateEVars(density)

    return functions.asSequence()
            .map { f ->
                eVars.asSequence()
                        .map { e ->
                            calculateSum(xVars, f, e)
                        }.toList()
            }.toList()
}

private fun generateEVars(density: Int): List<Int> = ArrayList<Int>().apply {
    for (i in 0 until density) {
        this.add(2.0.pow(i.toDouble()).toInt())
    }
}

private fun generateXVars(numOfVariants: Int): List<Int> = ArrayList<Int>().apply {
    for (i in 0 until numOfVariants) {
        this.add(i)
    }
}

private fun calculateSum(xVars: List<Int>, f: Function, e: Int): Int = xVars.asSequence()
        .map { x -> f(x) xor f(x xor e) }
        .sum()