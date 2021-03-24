package com.kthulhu.endportalfinder.domain

import com.kthulhu.endportalfinder.domain.evaluation.Point
import com.kthulhu.endportalfinder.domain.evaluation.Portal

class PortalFactory {
    fun findPortal(pointA: Point, pointB: Point): Portal {
        return Portal(pointA, pointB).apply {
            findX()
            findZ()
            findErrX()
            findErrZ()
            checkErrorType()
        }
    }
}