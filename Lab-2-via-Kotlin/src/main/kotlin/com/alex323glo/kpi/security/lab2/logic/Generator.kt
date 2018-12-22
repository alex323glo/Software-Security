package com.alex323glo.kpi.security.lab2.logic

import com.alex323glo.kpi.security.lab2.data.SBlock
import com.alex323glo.kpi.security.lab2.exception.GenerationException
import kotlin.math.pow

fun generateSBlock(density: Int): SBlock {
    val sBlocksVariants = generateAllPossibleSBlocks(density)

    for (sBlock in sBlocksVariants) {
        if (verifySBlock(sBlock)) {
            return sBlock
        }
    }

    throw GenerationException("Couldn't generate $density-position SBlock.")
}

private fun generateAllPossibleSBlocks(density: Int): List<SBlock> {
    val numOfVariableVariants = 2.0.pow(density.toDouble()).toInt()
    val variableVariants = ArrayList<Int>().apply {
        for (i in 0 until numOfVariableVariants) {
            this.add(i)
        }
    }

    val sequencesVariants = getSubLists(variableVariants.toIntArray(), intArrayOf())

    return sequencesVariants.map {
        SBlock(density, *it)
    }
}

//private fun getSubLists(sourceList: MutableList<Int>): List<List<Int>> = if (sourceList.size < 2) {
//    listOf(sourceList)
//} else {
//    sourceList
//            .asSequence()
//            .mapIndexed { index, element ->
//                val subList = sourceList.subList(0, index) + sourceList.subList(index, sourceList.size)
//                val result = mutableListOf(listOf(element))
//                result.addAll(getSubLists(subList.toMutableList()))
//                result
//            }
//            .flatMap { it.asSequence() }
//            .toList()
//}

private fun getSubLists(source: IntArray, accumulator: IntArray): MutableList<IntArray> {
    if (source.size < 2) {
        val local = accumulator + source
        return mutableListOf(local)
    }

    val result = mutableListOf<IntArray>()

    for (i in 0 until source.size) {
        val localSource = source.toMutableList()
        val localAccumulator = accumulator.toMutableList()

        val removedElement = localSource.removeAt(i)
        localAccumulator.add(removedElement)

        val subLists = getSubLists(localSource.toIntArray(), localAccumulator.toIntArray())
//        val localResult = subLists.forEach { array ->
//            result.add(intArrayOf(removedElement) + array)
//        }
        result.addAll(subLists)
    }

    return result
}

