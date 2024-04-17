package com.reva.revashoppy.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reva.revashoppy.databinding.ItemArrivalRowBinding

class ArrivalsAdapter(private val dataList: List<String>) :
    RecyclerView.Adapter<ArrivalsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArrivalRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(private val binding: ItemArrivalRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvProductName.text = item
            // Bind other views here
        }
    }
}
