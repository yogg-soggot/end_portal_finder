package com.kthulhu.endportalfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portal_table", primaryKeys = ["x", "z"])
data class PortalData(
    val x: Int,
    val z: Int,
    val errorX: Int,
    val errorZ: Int,
    val hasTrueCoordinates: Boolean
)