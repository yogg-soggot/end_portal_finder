package com.kthulhu.endportalfinder

import android.app.Application
import android.content.Context
import com.kthulhu.endportalfinder.di.AppComponent
import com.kthulhu.endportalfinder.di.AppModule
import com.kthulhu.endportalfinder.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}