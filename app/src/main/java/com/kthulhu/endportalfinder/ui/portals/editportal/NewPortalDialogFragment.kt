package com.kthulhu.endportalfinder.ui.portals.editportal

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.frament_edit_portal.*

class NewPortalDialogFragment : BaseEditPortalDialog() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        check_box_actual_cords.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                changeErrorVisibility(View.INVISIBLE)
                edit_error_x.setText("")
                edit_error_z.setText("")
            } else changeErrorVisibility(View.VISIBLE)
        }

        button_save.setOnClickListener{
            onSaveClicked()
        }
    }

    companion object {
        const val TAG = "NewPortalDialogFragment"
    }
}