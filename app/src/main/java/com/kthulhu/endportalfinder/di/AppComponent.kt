package com.kthulhu.endportalfinder.di


import com.kthulhu.endportalfinder.ui.finder.FinderFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: FinderFragment)
}