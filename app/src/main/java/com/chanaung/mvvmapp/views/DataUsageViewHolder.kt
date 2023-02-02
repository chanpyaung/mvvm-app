package com.chanaung.mvvmapp.views

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.databinding.DataUsageItemBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class DataUsageViewHolder(private val dataUsageItemBinding: DataUsageItemBinding) : ViewHolder(dataUsageItemBinding.root) {

    fun bind(dataUsage: DataUsage) {
        dataUsageItemBinding.yearTextView.text = "YEAR ${dataUsage.year}"
        val decimalFormat = DecimalFormat("#,###.######")
        val mobileData = decimalFormat.format(dataUsage.totalUsage)
        dataUsageItemBinding.usageTextView.text = "$mobileData PB"
    }
}