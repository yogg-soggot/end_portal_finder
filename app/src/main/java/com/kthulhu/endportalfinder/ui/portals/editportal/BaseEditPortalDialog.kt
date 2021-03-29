package com.kthulhu.endportalfinder.ui.portals.editportal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.kthulhu.endportalfinder.R
import com.kthulhu.endportalfinder.data.PortalData
import com.kthulhu.endportalfinder.ui.MainActivity
import com.kthulhu.endportalfinder.ui.MainViewModel
import kotlinx.android.synthetic.main.frament_edit_portal.*

open class BaseEditPortalDialog: DialogFragment() {

    protected lateinit var viewModel: MainViewModel
    protected lateinit var portal: PortalData
    protected lateinit var newPortal: PortalData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frament_edit_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).mainViewModel
    }

    protected fun changeErrorVisibility(_visibility: Int) {
        edit_error_x.visibility = _visibility
        edit_error_z.visibility = _visibility
        text_plus_minus_1.visibility = _visibility
        text_plus_minus_2.visibility = _visibility
    }

    protected open fun onSaveClicked(){
        try {
            newPortal = createUpdatedPortal()
        } catch (e: CoordinatesInputException) {
            showInputError()
            return
        }
    }

    protected fun showPortalData(){
        portal.apply {
            name?.let { edit_name.setText(it) }
            edit_x.setText(x.toString())
            edit_z.setText(z.toString())
            edit_error_x.setText(errorX.toString())
            edit_error_z.setText(errorZ.toString())
        }
    }

    private fun createUpdatedPortal(): PortalData {
        var name: String? = edit_name.text.toString()
        if(name == "") name = null

        val x: Int
        val y: Int
        var errX: Int
        var errZ: Int

        try {
            x = edit_x.text.toString().toInt()
            y = edit_z.text.toString().toInt()
        } catch (e: NumberFormatException) {
            throw CoordinatesInputException()
        }

        try {
            errX = edit_error_x.text.toString().toInt()
            errZ = edit_error_z.text.toString().toInt()
        } catch (e: NumberFormatException) {
            errX = 0
            errZ = 0
        }

        return PortalData(
            x,
            y,
            errX,
            errZ,
            name
        )
    }

    private fun showInputError(){
        Snackbar.make(requireView(), resources.getString(R.string.error_coordinates_not_entered), Snackbar.LENGTH_LONG).show()
    }

    class CoordinatesInputException : Exception()
}