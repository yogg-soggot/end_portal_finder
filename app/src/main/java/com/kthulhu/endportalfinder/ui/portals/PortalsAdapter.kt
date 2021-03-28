package com.kthulhu.endportalfinder.ui.portals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kthulhu.endportalfinder.R
import com.kthulhu.endportalfinder.data.PortalData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalsAdapter(
    private val onRemoveBtnClick: (portal: PortalData) -> Unit,
    private val onEditBtnClick: (portal: PortalData) -> Unit
) : RecyclerView.Adapter<PortalsAdapter.ViewHolder>() {

    private var data = listOf<PortalData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        return ViewHolder(view, onRemoveBtnClick, onEditBtnClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        override val containerView: View,
        private val onRemoveBtnClick: (portal: PortalData) -> Unit,
        private val onEditBtnClick: (portal: PortalData) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(data: PortalData){
            containerView.apply {
                portal_name.visibility = View.GONE
                data.name?.let {
                    portal_name.text = it
                    portal_name.visibility = View.VISIBLE
                }
                tv_x.text = resources.getString(
                    R.string.portalx,
                    data.x, data.errorX
                )
                tv_z.text = resources.getString(
                    R.string.portalz,
                    data.z, data.errorZ
                )
                btn_delete.setOnClickListener { onRemoveBtnClick(data) }
                btn_edit.setOnClickListener { onEditBtnClick(data) }
            }
        }
    }

    fun setPortals(portals: List<PortalData>){
        data = portals.reversed()
        notifyDataSetChanged()
    }
}