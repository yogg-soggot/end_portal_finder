package com.kthulhu.endportalfinder.ui.portals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kthulhu.endportalfinder.R
import com.kthulhu.endportalfinder.data.PortalData
import com.kthulhu.endportalfinder.ui.MainActivity
import com.kthulhu.endportalfinder.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_portals.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PortalsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PortalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).mainViewModel

        adapter = PortalsAdapter(::onRemoveClicked, ::onEditClicked)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            viewModel.getPortals().collect {
                adapter.setPortals(it)
            }
        }
    }

    private fun onRemoveClicked(portal: PortalData){
        viewModel.deletePortal(portal)
    }

    private fun onEditClicked(portal: PortalData){
        viewModel.editedPortal = portal
        EditPortalDialogFragment().show(childFragmentManager, EditPortalDialogFragment.TAG)
    }
}