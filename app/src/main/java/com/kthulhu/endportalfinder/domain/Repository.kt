package com.kthulhu.endportalfinder.domain

import com.kthulhu.endportalfinder.data.PortalRoomDatabase
import com.kthulhu.endportalfinder.domain.evaluation.Portal
import javax.inject.Inject

class Repository @Inject constructor (
    private val database: PortalRoomDatabase
) {
    private val dao = database.portalDao()

    suspend fun savePortal(portal: Portal, hasTrueCords: Boolean = false){
        dao.insert(portal.convertToEntity(hasTrueCords))
    }
}