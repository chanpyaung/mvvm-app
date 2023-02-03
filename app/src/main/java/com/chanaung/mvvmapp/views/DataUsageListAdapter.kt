package com.chanaung.mvvmapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.databinding.DataUsageItemBinding

class DataUsageListAdapter(private val dataUsages: List<DataUsage>, private val onClickListener: ViewItemClickListener): Adapter<DataUsageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataUsageViewHolder {
        val binding = DataUsageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataUsageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataUsageViewHolder, position: Int) {
        holder.bind(dataUsages[position])
        holder.itemView.rootView.setOnClickListener {
            onClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int = dataUsages.size

}