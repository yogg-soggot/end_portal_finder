package com.kthulhu.endportalfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portal_table")
data class PortalData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val x: Int,
    val z: Int,
    val errorX: Int,
    val errorZ: Int,
    val hasTrueCoordinates: Boolean = false
)