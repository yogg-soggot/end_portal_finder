package com.kthulhu.endportalfinder.ui.usageguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kthulhu.endportalfinder.R
import kotlinx.android.synthetic.main.fragment_usage_guide.*

class UsageGuideFragment : Fragment() {

    private val viewModel: UsageGuideViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usage_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pdfView.fromAsset("Usage Guide.pdf")
            .defaultPage(viewModel.currentPage)
            .onPageChange { page, _ ->  viewModel.currentPage = page}
            .onRender { _, _, _ ->
                pdfView.fitToWidth(viewModel.currentPage)
            }
            .load()
    }
}