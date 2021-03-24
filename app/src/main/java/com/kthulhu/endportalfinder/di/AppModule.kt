package com.kthulhu.endportalfinder.di

import android.content.Context
import com.kthulhu.endportalfinder.data.PortalRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideRoom(): PortalRoomDatabase {
        return PortalRoomDatabase.getDatabase(context)
    }

    @Provides
    fun provideContext(): Context{
        return context
    }
}