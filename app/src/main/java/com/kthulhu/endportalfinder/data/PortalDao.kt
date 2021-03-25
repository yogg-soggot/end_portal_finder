package com.kthulhu.endportalfinder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PortalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(portalData: PortalData): Void

    @Delete
    suspend fun delete(portalData: PortalData): Void

    @Query("SELECT * FROM portal_table")
    fun loadAll(): Flow<List<PortalData>>
}