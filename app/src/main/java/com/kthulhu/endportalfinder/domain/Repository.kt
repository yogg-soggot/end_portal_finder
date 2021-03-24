package com.kthulhu.endportalfinder.domain

import com.kthulhu.endportalfinder.data.PortalRoomDatabase
import javax.inject.Inject

class Repository @Inject constructor (
    private val database: PortalRoomDatabase
) {
}