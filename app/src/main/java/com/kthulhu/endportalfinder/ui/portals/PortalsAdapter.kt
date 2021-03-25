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
    private val onRemoveBtnClick: (portal: PortalData) -> Unit
) : RecyclerView.Adapter<PortalsAdapter.ViewHolder>() {

    private var data = listOf<PortalData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        return ViewHolder(view, onRemoveBtnClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        override val containerView: View,
        private val onRemoveBtnClick: (portal: PortalData) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(data: PortalData){
            containerView.tv_x.text = containerView.resources.getString(
                R.string.portalx,
                data.x, data.errorX
            )
            containerView.tv_z.text = containerView.resources.getString(
                R.string.portalz,
                data.z, data.errorZ
            )
            containerView.btn_delete.setOnClickListener {
                onRemoveBtnClick(data)
            }
        }
    }

    fun setPortals(portals: List<PortalData>){
        data = portals.reversed()
        notifyDataSetChanged()
    }
}