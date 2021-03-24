package com.kthulhu.endportalfinder.ui.finder

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kthulhu.endportalfinder.App
import com.kthulhu.endportalfinder.R
import com.kthulhu.endportalfinder.domain.PortalFactory
import com.kthulhu.endportalfinder.domain.evaluation.EvaluationError
import com.kthulhu.endportalfinder.domain.evaluation.Point
import kotlinx.android.synthetic.main.fragment_finder.*
import java.lang.NumberFormatException
import javax.inject.Inject

class FinderFragment : Fragment() {

    @Inject
    lateinit var finderViewModel: FinderViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as App).appComponent.inject(this)

        displayPortalCoordinates()

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
            finderViewModel.findPortal(a, b)
            displayPortalCoordinates()
            showErrorDialogIfNeeded()
        }

        floatingActionButton.setOnClickListener {
            finderViewModel.savePortal(false) //TODO("Добавить возможность ввести фактические координаты")
            finderViewModel.portal?.let{
                Snackbar.make(requireView(), rgS(R.string.portal_saved), Snackbar.LENGTH_LONG).show()
            }
        }

    }

    private fun showErrorDialogIfNeeded(){
        fun showError(error: String){
            AlertDialog.Builder(requireContext())
                .setMessage(error)
                .setTitle(rgS(R.string.attention))
                .setNegativeButton(rgS(android.R.string.ok)){ di: DialogInterface, _ ->
                    di.cancel()
                }.show()
        }
        when(finderViewModel.getErrorType()) {
            EvaluationError.MODERATE -> showError(rgS(R.string.error_moderate))
            EvaluationError.CRITICAL -> showError(rgS(R.string.error_critical))
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

    private fun rgS(id: Int): String{
        return resources.getString(id)
    }
}
