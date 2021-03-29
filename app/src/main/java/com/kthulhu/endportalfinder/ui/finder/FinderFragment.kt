package com.kthulhu.endportalfinder.ui.finder

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.kthulhu.endportalfinder.R
import com.kthulhu.endportalfinder.domain.evaluation.EvaluationError
import com.kthulhu.endportalfinder.domain.evaluation.Point
import com.kthulhu.endportalfinder.ui.MainActivity
import com.kthulhu.endportalfinder.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_finder.*
import kotlinx.android.synthetic.main.item_portal.*

class FinderFragment : Fragment() {

    lateinit var vm: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = (activity as MainActivity).mainViewModel

        vm.finderState.onFragmentStarted(this)

        button_compute.setOnClickListener {
            val a: Point
            val b: Point
            try {
                a = toPoint(point_1_x, point_1_z, point_1_angle)
                b = toPoint(point_2_x, point_2_z, point_2_angle)
            } catch (e: NumberFormatException) {
                Snackbar.make(requireView(), rgS(R.string.not_all_fields_entered), Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            vm.findPortal(a, b)

            vm.finderState = when(vm.portal.errorType) {
                EvaluationError.NORMAL -> FoundSuccessfully()
                EvaluationError.INVALID_PORTAL -> PortalIsInvalid
                else -> EvaluationErrorIsHigh
            }

            vm.finderState.onPortalFound(this)

        }

        floatingActionButton.setOnClickListener {
            vm.finderState.onSaveClicked(this)
        }

    }

    fun warnTooHighError(){
        when(vm.getErrorType()) {
            EvaluationError.MODERATE -> showError(rgS(R.string.error_moderate))
            EvaluationError.CRITICAL -> showError(rgS(R.string.error_critical))
            else -> return
        }
    }

    fun displayPortalCoordinates(){
        r_portal_x.text = resources.getString(R.string.portalx, vm.portal.x, vm.portal.errorX)
        r_portal_z.text = resources.getString(R.string.portalz, vm.portal.z, vm.portal.errorZ)
    }

    fun showError(error: String){
        AlertDialog.Builder(requireContext())
            .setMessage(error)
            .setTitle(rgS(R.string.attention))
            .setNegativeButton(rgS(android.R.string.ok)){ di: DialogInterface, _ ->
                di.cancel()
            }.show()
    }

    private fun toPoint(x: TextView, z: TextView, angle: TextView): Point{
        fun TextView.getInt() = this.text.toString().toInt()
        fun TextView.getDouble() = this.text.toString().toDouble()
        return Point(x.getInt(), z.getInt(), angle.getDouble())
    }

    fun rgS(id: Int): String {
        return resources.getString(id)
    }
}
