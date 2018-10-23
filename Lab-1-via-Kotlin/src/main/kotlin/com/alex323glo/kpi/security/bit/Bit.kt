package com.alex323glo.kpi.security.bit

import com.alex323glo.kpi.security.exception.ToBitConvertionException

/**
 * TODO add doc!!!
 */
enum class Bit(val value: Boolean) {
    ZERO(false),
    UNIT(true);

    companion object {

        fun of(booleanValue: Boolean) = if (booleanValue) UNIT else ZERO

        fun of(intValue: Int) = when (intValue) {
            0 -> Bit.ZERO
            1 -> Bit.UNIT
            else -> throw ToBitConvertionException("Can't convert Integer to Bit, when it's not equal to 0 or 1.")
        }

    }
}