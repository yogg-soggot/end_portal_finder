package com.kthulhu.endportalfinder.domain.evaluation

import kotlin.math.cos
import kotlin.math.sin

fun Double.toRadians() = Math.toRadians(this)

fun sec(angle: Double) :Double = 1/ cos(angle.toRadians())
fun csc(angle: Double): Double = 1/ sin(angle.toRadians())

fun scalarProduct(v1: Pair<Int, Int>, v2: Pair<Double, Double>): Double {
    return v1.first * v2.first + v1.second * v2.second
}

