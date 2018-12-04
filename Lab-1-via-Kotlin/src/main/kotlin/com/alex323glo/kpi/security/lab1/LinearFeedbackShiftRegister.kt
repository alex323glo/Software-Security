package com.alex323glo.kpi.security.lab1

import com.alex323glo.kpi.security.lab1.data.Bit
import com.alex323glo.kpi.security.lab1.data.Bit.UNIT
import com.alex323glo.kpi.security.lab1.exception.LFSRException
import java.util.*

class LinearFeedbackShiftRegister (
        val feedbackFunction: List<Bit>,
        params: List<Bit>
) : PseudoRandomNumberGenerator<Bit> {

    val density = feedbackFunction.size + 1
    val register: LinkedList<Bit> = initRegister(params)

    override fun next(): Bit {
        var buffer = register.first
        for (index in 0 until feedbackFunction.size) {
            if (feedbackFunction[index] == UNIT) {
                buffer %= register[index + 1]
            }
        }
        register.add(buffer)
        return register.poll()
    }

    private fun initRegister(params: List<Bit>): LinkedList<Bit> = if (params.size == density) {
        LinkedList(params)
    } else {
        throw LFSRException("Register initiation failed: Wrong size of params list (${params.size} != $density)")
    }

    override fun toString(): String = "{state: $register; f: $feedbackFunction}"
}
