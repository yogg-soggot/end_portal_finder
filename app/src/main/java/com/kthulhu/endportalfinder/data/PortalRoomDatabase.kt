package com.kthulhu.endportalfinder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PortalData::class], version = 2)
abstract class PortalRoomDatabase : RoomDatabase(){
    abstract fun portalDao(): PortalDao

    companion object {
        @Volatile
        private var INSTANCE: PortalRoomDatabase? = null

        fun getDatabase(context: Context): PortalRoomDatabase {
            INSTANCE?.let {return it}

            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    PortalRoomDatabase::class.java,
                    "portal_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}