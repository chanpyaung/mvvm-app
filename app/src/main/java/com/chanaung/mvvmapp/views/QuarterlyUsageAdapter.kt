package com.chanaung.mvvmapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chanaung.mvvmapp.databinding.UsageItemViewBinding
import java.text.DecimalFormat
import kotlin.math.roundToInt

class QuarterlyUsageAdapter(private val usageItems: List<UsageItem>) : Adapter<QuarterlyUsageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuarterlyUsageViewHolder {
        val binding = UsageItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuarterlyUsageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuarterlyUsageViewHolder, position: Int) {
        holder.bind(usageItems[position])
    }

    override fun getItemCount(): Int = usageItems.size
}

class QuarterlyUsageViewHolder(private val usageItemBinding: UsageItemViewBinding): ViewHolder(usageItemBinding.root) {

    fun bind(usageItem: UsageItem) {

        usageItemBinding.apply {
            indicator.max = 100
            indicator.progress = ( usageItem.usage / usageItem.totalUsage ).times(100).roundToInt()
            indicator.animate()
            title.text = usageItem.period
            val decimalFormat = DecimalFormat("#,###.######")
            val mobileData = decimalFormat.format(usageItem.usage)
            valueText.text = "$mobileData PB"
        }
    }

}

data class UsageItem(
    val period: String,
    val usage: Double,
    val totalUsage: Double
)