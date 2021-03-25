package com.kthulhu.endportalfinder.di


import com.kthulhu.endportalfinder.ui.MainActivity
import com.kthulhu.endportalfinder.ui.finder.FinderFragment
import com.kthulhu.endportalfinder.ui.portals.PortalsFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}