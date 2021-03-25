package com.kthulhu.endportalfinder.domain

import com.kthulhu.endportalfinder.data.PortalDao
import com.kthulhu.endportalfinder.data.PortalData
import com.kthulhu.endportalfinder.data.PortalRoomDatabase
import com.kthulhu.endportalfinder.domain.evaluation.Portal
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor (
    private val dao: PortalDao
) {
    suspend fun savePortal(portal: Portal, hasTrueCords: Boolean = false){
        dao.insert(portal.convertToEntity(hasTrueCords))
    }

    fun loadPortals(): Flow<List<PortalData>> {
        return dao.loadAll()
    }

    suspend fun deletePortal(portal: PortalData) {
        dao.delete(portal)
    }
}