package com.reva.revashoppy.ui.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.reva.revashoppy.databinding.FragmentHomeBinding
import com.reva.revashoppy.ui.dashboard.adapter.ArrivalsAdapter
import com.reva.revashoppy.ui.dashboard.adapter.CatalogAdapter


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val dataList = listOf("Gold Ring", "Platinum Ring", "Gold Ring", "Gold Ring", "Gold Ring", "Gold Ring")

        // Set up RecyclerView
        binding.rvProductList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvProductList.adapter = ArrivalsAdapter(dataList)


        binding.rvCatalogList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCatalogList.adapter = CatalogAdapter(dataList)


        binding.rvPopularProList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularProList.adapter = ArrivalsAdapter(dataList)

        // Inflate the layout for this fragment
        return binding.root
    }


}