package com.kthulhu.endportalfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kthulhu.endportalfinder.data.PortalData
import com.kthulhu.endportalfinder.domain.Repository
import com.kthulhu.endportalfinder.domain.evaluation.EvaluationError
import com.kthulhu.endportalfinder.domain.evaluation.Point
import com.kthulhu.endportalfinder.domain.evaluation.Portal
import com.kthulhu.endportalfinder.domain.evaluation.PortalFactory
import com.kthulhu.endportalfinder.ui.finder.FinderState
import com.kthulhu.endportalfinder.ui.finder.PortalNotFoundYet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val factory: PortalFactory,
    private val repository: Repository
) : ViewModel() {

    lateinit var portal: Portal
    lateinit var editedPortal: PortalData

    var finderState: FinderState = PortalNotFoundYet

    fun findPortal(a: Point, b: Point) {
        portal = factory.findPortal(a, b)
    }

    fun getErrorType(): EvaluationError {
        return portal.errorType
    }

    fun savePortal(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePortal(portal)
        }
    }

    fun savePortal(portal: PortalData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePortal(portal)
        }
    }

    fun getPortals(): Flow<List<PortalData>> {
        return repository.loadPortals()
    }

    fun deletePortal(portalData: PortalData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePortal(portalData)
        }
    }

    fun updatePortal(portal: PortalData, newPortal: PortalData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePortal(portal, newPortal)
        }
    }
}