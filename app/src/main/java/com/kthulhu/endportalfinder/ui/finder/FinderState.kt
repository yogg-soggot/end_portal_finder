package com.kthulhu.endportalfinder.ui.finder

import com.google.android.material.snackbar.Snackbar
import com.kthulhu.endportalfinder.R
import kotlinx.android.synthetic.main.fragment_finder.*


sealed class FinderState {
    open fun onFragmentStarted(fragment: FinderFragment) = Unit
    open fun onSaveClicked(fragment: FinderFragment) = Unit
    open fun onPortalFound(fragment: FinderFragment) = Unit
}

object PortalNotFoundYet : FinderState()

open class FoundSuccessfully: FinderState(){

    override fun onFragmentStarted(fragment: FinderFragment) {
        fragment.displayPortalCoordinates()
    }

    override fun onPortalFound(fragment: FinderFragment) {
        fragment.displayPortalCoordinates()
    }

    override fun onSaveClicked(fragment: FinderFragment) {
        fragment.apply {
            vm.savePortal()
            Snackbar.make(requireView(), rgS(R.string.portal_saved), Snackbar.LENGTH_LONG).show()
        }
    }
}

object EvaluationErrorIsHigh: FoundSuccessfully() {
    override fun onPortalFound(fragment: FinderFragment) {
        super.onPortalFound(fragment)
        fragment.warnTooHighError()
    }
}

object PortalIsInvalid : FinderState() {
    override fun onPortalFound(fragment: FinderFragment) {
        fragment.apply {
            showError(rgS(R.string.error_invalid_portal))
            r_portal_x.text = ""
            r_portal_z.text = ""
        }
    }
}