package com.kthulhu.endportalfinder.ui.finder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kthulhu.endportalfinder.domain.Repository
import com.kthulhu.endportalfinder.domain.evaluation.PortalFactory
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val factory: PortalFactory,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            PortalFactory::class.java,
            Repository::class.java
        ).newInstance(factory, repository)
    }
}