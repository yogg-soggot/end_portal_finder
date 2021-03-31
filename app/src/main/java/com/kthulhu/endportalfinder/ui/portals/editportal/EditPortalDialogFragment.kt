package com.kthulhu.endportalfinder.ui.portals.editportal

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.frament_edit_portal.*

class EditPortalDialogFragment : BaseEditPortalDialog() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        portal = viewModel.editedPortal

        showPortalData()

        check_box_actual_cords.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                changeErrorVisibility("0", "0", View.INVISIBLE)
            } else changeErrorVisibility(
                portal.errorX.toString(),
                portal.errorZ.toString(),
                View.VISIBLE
            )
        }

        button_save.setOnClickListener {
            onSaveClicked {
                viewModel.updatePortal(portal, newPortal)
            }
        }
    }

    private fun changeErrorVisibility(errorX: String, errorZ: String, _visibility: Int){
        changeErrorVisibility(_visibility)
        edit_error_x.setText(errorX)
        edit_error_z.setText(errorZ)
    }

    companion object {
        const val TAG = "EditPortalDialogFragment"
    }
}