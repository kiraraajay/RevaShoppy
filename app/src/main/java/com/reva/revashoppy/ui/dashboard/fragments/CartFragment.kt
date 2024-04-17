package com.reva.revashoppy.ui.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reva.revashoppy.R
import com.reva.revashoppy.databinding.FragmentCartBinding
import com.reva.revashoppy.databinding.FragmentHomeBinding

class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.headerLayout.tvScreenName.text= getString(R.string.cart)

        // Inflate the layout for this fragment
        return binding.root
    }


}