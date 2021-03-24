package com.kthulhu.endportalfinder.ui.finder

import androidx.lifecycle.ViewModel
import com.kthulhu.endportalfinder.domain.PortalFactory
import com.kthulhu.endportalfinder.domain.Repository
import com.kthulhu.endportalfinder.domain.evaluation.EvaluationError
import com.kthulhu.endportalfinder.domain.evaluation.Point
import com.kthulhu.endportalfinder.domain.evaluation.Portal
import javax.inject.Inject

class FinderViewModel @Inject constructor(
    private val factory: PortalFactory,
    private val repository: Repository
    ) : ViewModel() {

    var portal: Portal? = null

    fun findPortal(a: Point, b: Point) {
        portal = factory.findPortal(a, b)
    }

    fun getErrorType(): EvaluationError{
        return portal?.errorType ?: EvaluationError.NORMAL
    }

}