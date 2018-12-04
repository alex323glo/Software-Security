package com.alex323glo.kpi.security.lab1

interface PseudoRandomNumberGenerator<T> {
    fun next(): T
}