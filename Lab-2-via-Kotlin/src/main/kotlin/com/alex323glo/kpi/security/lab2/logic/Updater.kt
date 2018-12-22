package com.alex323glo.kpi.security.lab2.logic

import com.alex323glo.kpi.security.lab2.data.Function
import com.alex323glo.kpi.security.lab2.data.SBlock
import com.alex323glo.kpi.security.lab2.exception.GenerationException
import com.alex323glo.kpi.security.lab2.util.generateXVars
import kotlin.math.pow

private val DEFAULT_E_GENERATION_STRATEGY: (Int) -> Int = { 1 }

fun updateSBlock(sBlock: SBlock, targetDensity: Int): SBlock {
    val currentDensity = sBlock.density
    var resultSBlock: SBlock = sBlock
    repeat(targetDensity - currentDensity) {
        resultSBlock = incrementSBlockDensity(resultSBlock)
    }

    return if (verifySBlock(resultSBlock)) {
        resultSBlock
    } else {
        throw GenerationException("Can't generate SBlock of targetDensity=$targetDensity.")
    }
}

private fun incrementSBlockDensity(sBlock: SBlock): SBlock {
    val f = sBlock.functions.last()
    val e = DEFAULT_E_GENERATION_STRATEGY(sBlock.density)
    val xVars = generateXVars(sBlock.density)
    val argument = generateArgument(xVars, e)

    val functionContinuation = generateFunctionContinuation(argument, f)

    val resultFunction = functionContinuation + f

    val sBlockContinuation = generateSBlockContinuation(argument, sBlock)

    val resultSBlock = sBlock + sBlockContinuation

    return assembleIncrementedSBlock(resultFunction, resultSBlock)
}

private fun assembleIncrementedSBlock(resultFunction: Function, resultSBlock: SBlock): SBlock =
        resultFunction.vector
                .mapIndexed { index, bit ->
                    bit.value * 2.0.pow(resultFunction.density - 1).toInt() + resultSBlock.numbers[index]
                }
                .toIntArray()
                .let {
                    SBlock(resultSBlock.density, *it)
                }

private fun generateArgument(xVars: List<Int>, e: Int) =
        xVars.map { x -> x xor e }

private fun generateFunctionContinuation(argument: List<Int>, f: Function): Function =
        argument.map { x -> f(x) xor 1 }
                .toIntArray()
                .let { Function(*it) }

private fun generateSBlockContinuation(argument: List<Int>, sBlock: SBlock): SBlock =
        argument.map { x -> sBlock(x) }
                .toIntArray()
                .let { SBlock(sBlock.density, *it) }
