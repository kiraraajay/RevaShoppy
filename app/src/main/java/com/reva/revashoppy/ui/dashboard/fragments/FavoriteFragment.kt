package com.reva.revashoppy.ui.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.reva.revashoppy.R
import com.reva.revashoppy.databinding.FragmentFavoriteBinding
import com.reva.revashoppy.databinding.FragmentHomeBinding
import com.reva.revashoppy.ui.dashboard.adapter.ArrivalsAdapter

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)


        val dataList = listOf("Gold Ring", "Platinum Ring", "Gold Ring", "Gold Ring", "Gold Ring", "Gold Ring")

        binding.rvFavoriteList.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvFavoriteList.adapter = ArrivalsAdapter(dataList)


        return binding.root
    }


}