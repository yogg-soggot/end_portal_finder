package com.kthulhu.endportalfinder.domain.evaluation

import javax.inject.Inject

class PortalFactory @Inject constructor() {
    fun findPortal(pointA: Point, pointB: Point): Portal {
        return Portal(pointA, pointB).apply {
            findX()
            findZ()
            findErrX()
            findErrZ()
            checkErrorType()
            checkIsPortalValid()
        }
    }
}