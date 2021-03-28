package com.kthulhu.endportalfinder.domain

import com.kthulhu.endportalfinder.data.PortalDao
import com.kthulhu.endportalfinder.data.PortalData
import com.kthulhu.endportalfinder.domain.evaluation.Portal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class Repository @Inject constructor (
    private val dao: PortalDao
) {
    suspend fun savePortal(portal: Portal){
        dao.insert(portal.convertToEntity())
    }

    fun loadPortals(): Flow<List<PortalData>> {
        return dao.loadAll().distinctUntilChanged()
    }

    suspend fun deletePortal(portal: PortalData) {
        dao.delete(portal)
    }

    suspend fun updatePortal(portal: PortalData, newPortal: PortalData) {
        if(isPrimaryKeySame(portal, newPortal)) {
            dao.update(newPortal)
        } else {
            dao.delete(portal)
            dao.insert(newPortal)
        }
    }

    private fun isPrimaryKeySame(portal: PortalData, newPortal: PortalData): Boolean {
        return portal.x == newPortal.x && portal.z == newPortal.z
    }
}