package com.kthulhu.endportalfinder.domain.evaluation


import kotlin.math.*
import kotlin.properties.Delegates.notNull


class Portal(private val a: Point, private val b: Point) {

    var x by notNull<Int>()
    var z by notNull<Int>()
    var errorX by notNull<Int>()
    var errorZ by notNull<Int>()

    var isNotValid by notNull<Boolean>()

    var errorType = EvaluationError.NORMAL

    fun findX(){
        val numerator = b.x*csc(b.angle)*sec(a.angle) - (a.x*csc(a.angle)+(a.z-b.z)*sec(a.angle))*sec(b.angle)
        val denominator = csc(b.angle)*sec(a.angle)-csc(a.angle)*sec(b.angle)

        x = (numerator/denominator).toInt()
    }

    fun findZ(){
        val numerator = a.z*csc(b.angle)*sec(a.angle) + csc(a.angle)*((a.x-b.x)*csc(b.angle)-b.z*sec(b.angle))
        val denominator = csc(b.angle)*sec(a.angle)-csc(a.angle)*sec(b.angle)

        z = (numerator/denominator).toInt()
    }

    fun findErrX(){
        val numerator = csc(b.angle).pow(4)*sec(a.angle).pow(2)*((-a.x+b.x)*csc(a.angle)+(-a.z+b.z)*sec(a.angle)).pow(2)*sec(b.angle).pow(4)+csc(a.angle).pow(4)*sec(a.angle).pow(4)*sec(b.angle).pow(2)*((-a.x+b.x)*csc(b.angle)+(-a.z+b.z)*sec(b.angle)).pow(2)
        val denominator = (csc(b.angle)*sec(a.angle)-csc(a.angle)*sec(b.angle)).pow(4)
        errorX = (PI/180* sqrt(numerator/denominator)).toInt()
    }
    fun findErrZ(){
        val numerator = csc(a.angle).pow(2)*csc(b.angle).pow(4)*((a.x-b.x)*csc(a.angle)+(a.z-b.z)*sec(a.angle)).pow(2)*sec(b.angle).pow(4)+csc(a.angle).pow(4)*csc(b.angle).pow(2)*sec(a.angle).pow(4)*((a.x-b.x)*csc(b.angle)+(a.z-b.z)*sec(b.angle)).pow(2)
        val denominator = (csc(b.angle)*sec(a.angle)-csc(a.angle)*sec(b.angle)).pow(4)
        errorZ = (PI/180* sqrt(numerator/denominator)).toInt()
    }

    fun checkErrorType(){
        errorType = when {
            errorX < NORMAL_DISTANCE && errorZ < NORMAL_DISTANCE -> EvaluationError.NORMAL
            errorX > LARGE_DISTANCE || errorZ > LARGE_DISTANCE -> EvaluationError.CRITICAL
            else -> EvaluationError.MODERATE
        }
    }

    fun checkIsPortalValid() {
        val vectorByPoints = Pair(x - a.x, z - a.z)
        val vectorByAngle = Pair(-sin(a.angle), cos(a.angle))
        if(scalarProduct(vectorByPoints, vectorByAngle) < 0){
            errorType = EvaluationError.INVALID_PORTAL
        }
    }

    companion object {
        const val NORMAL_DISTANCE = 250
        const val LARGE_DISTANCE = 700
    }
}