package com.kthulhu.endportalfinder.domain

import com.kthulhu.endportalfinder.data.PortalData
import com.kthulhu.endportalfinder.domain.evaluation.Portal

fun Portal.convertToEntity(
    hasTrueCords: Boolean
): PortalData{
    return PortalData(
        x = x,
        z = z,
        errorX = errorX,
        errorZ = errorZ,
        hasTrueCoordinates = hasTrueCords
    )
}