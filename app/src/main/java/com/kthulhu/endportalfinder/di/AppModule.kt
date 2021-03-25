package com.kthulhu.endportalfinder.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.kthulhu.endportalfinder.data.PortalDao
import com.kthulhu.endportalfinder.data.PortalRoomDatabase
import com.kthulhu.endportalfinder.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providePortalDao(): PortalDao {
        return PortalRoomDatabase.getDatabase(context).portalDao()
    }

    @Provides
    fun provideContext(): Context{
        return context
    }
}