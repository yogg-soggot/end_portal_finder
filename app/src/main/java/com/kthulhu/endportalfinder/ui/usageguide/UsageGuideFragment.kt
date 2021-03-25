package com.kthulhu.endportalfinder.ui.usageguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kthulhu.endportalfinder.R

class UsageGuideFragment : Fragment() {

    val usageGuideViewModel: UsageGuideViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usage_guide, container, false)
    }
}