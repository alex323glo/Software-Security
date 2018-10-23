package com.alex323glo.kpi.security

import com.alex323glo.kpi.security.bit.Bit
import java.util.*

class LFSR (val polynom: Array<Bit>) {

    val register: Array<Bit>
    val capacity: Int

    init {
        register = Array(polynom.size) { Bit.ZERO }
        capacity = register.size - 1

        for (i in 0..capacity) {
            register[i] = Bit.of(superRandom.nextBoolean())
        }

        runLFSRIteration()
    }

    fun generateStep(): Bit {
        runLFSRIteration()
        return register[0]
    }

    private fun runLFSRIteration() {
        val buffer = register[capacity]

        for (i in capacity downTo 1) {
            register[i] = when (polynom[i - 1]) {
                Bit.ZERO -> register[i - 1]
                Bit.UNIT -> Bit.of(register[i - 1].value or buffer.value)
            }
        }
        register[0] = buffer
    }




    companion object {
        @JvmStatic val superRandom = Random()
    }

}
