package com.chanaung.mvvmapp.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chanaung.mvvmapp.data.DataUsage

class ViewPagerAdapter(

    private val dataUsages: List<DataUsage>,
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = dataUsages.size

    override fun createFragment(position: Int): Fragment {
        return DetailsDataUsageFragment.newInstance(dataUsages[position])
    }

}