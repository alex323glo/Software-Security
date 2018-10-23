package com.alex323glo.kpi.security.bit

fun bitArrayOf(vararg bits: Int): Array<Bit> {
    val resultArray = Array(bits.size) { Bit.ZERO }
    if (bits.isEmpty()) return resultArray

    val bitsIterator = bits.iterator()
    for (i in 0 until resultArray.size) {
        resultArray[i] = Bit.of(bitsIterator.next())
    }

    return resultArray
}