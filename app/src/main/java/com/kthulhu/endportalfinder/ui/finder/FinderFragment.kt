package com.kthulhu.endportalfinder.ui.finder

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kthulhu.endportalfinder.R
import com.kthulhu.endportalfinder.domain.evaluation.EvaluationError
import com.kthulhu.endportalfinder.domain.evaluation.Point
import kotlinx.android.synthetic.main.fragment_finder.*
import java.lang.Exception
import java.lang.NumberFormatException

class FinderFragment : Fragment() {

    private val finderViewModel: FinderViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayPortalCoordinates()

        button_compute.setOnClickListener {
            val a: Point
            val b: Point
            try {
                a = toPoint(point_1_x, point_1_z, point_1_angle)
                b = toPoint(point_2_x, point_2_z, point_2_angle)
            } catch (e: NumberFormatException) {
                Snackbar.make(requireView(), getS(R.string.not_all_fields_entered), Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            finderViewModel.findPortal(a, b)
            displayPortalCoordinates()
            showErrorDialogIfNeeded()
        }


    }

    private fun showErrorDialogIfNeeded(){
        fun showError(error: String){
            AlertDialog.Builder(requireContext())
                .setMessage(error)
                .setTitle(getS(R.string.attention))
                .setNegativeButton(getS(android.R.string.ok)){di: DialogInterface, _ ->
                    di.cancel()
                }.show()
        }
        when(finderViewModel.getErrorType()) {
            EvaluationError.MODERATE -> showError(getS(R.string.error_moderate))
            EvaluationError.CRITICAL -> showError(getS(R.string.error_critical))
            else -> return
        }
    }

    private fun displayPortalCoordinates(){
        finderViewModel.portal?.let { portal ->
            r_portal_x.text = resources.getString(R.string.portalx, portal.x, portal.errorX)
            r_portal_z.text = resources.getString(R.string.portalz, portal.z, portal.errorZ)
        }
    }

    private fun toPoint(x: TextView, z: TextView, angle: TextView): Point{
        fun TextView.getInt() = this.text.toString().toInt()
        fun TextView.getDouble() = this.text.toString().toDouble()
        return Point(x.getInt(), z.getInt(), angle.getDouble())
    }

    private fun getS(id: Int): String{
        return resources.getString(id)
    }
}
