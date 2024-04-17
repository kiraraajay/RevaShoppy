package com.reva.revashoppy.ui.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reva.revashoppy.R
import com.reva.revashoppy.databinding.FragmentCategoryBinding
import com.reva.revashoppy.databinding.FragmentHomeBinding

class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)






        // Inflate the layout for this fragment
        return binding.root
    }

}