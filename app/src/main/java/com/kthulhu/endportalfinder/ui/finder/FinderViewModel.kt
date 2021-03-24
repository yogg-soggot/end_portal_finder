package com.kthulhu.endportalfinder.ui.finder

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kthulhu.endportalfinder.domain.PortalFactory
import com.kthulhu.endportalfinder.domain.evaluation.EvaluationError
import com.kthulhu.endportalfinder.domain.evaluation.Point
import com.kthulhu.endportalfinder.domain.evaluation.Portal

class FinderViewModel : ViewModel() {

    private val factory = PortalFactory()
    var portal: Portal? = null

    fun findPortal(a: Point, b: Point) {
        portal = factory.findPortal(a, b)
    }

    fun getErrorType(): EvaluationError{
        return portal?.errorType ?: EvaluationError.NORMAL
    }

}