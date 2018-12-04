package com.alex323glo.kpi.security.lab1.data

import com.alex323glo.kpi.security.lab1.exception.BitException
import java.util.*
import java.util.stream.Collectors

enum class Bit (val value: Int) {
    ZERO(0),
    UNIT(1);

    operator fun rem(funBit: Bit): Bit = if (this != funBit) UNIT else ZERO

    override fun toString(): String = value.toString()
}