package com.chanaung.mvvmapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.databinding.FragmentDetailsDataUsageBinding
import com.chanaung.mvvmapp.viewmodels.DetailsDataUsageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsDataUsageFragment : Fragment() {

    private lateinit var dataUsage: DataUsage
    private var _binding: FragmentDetailsDataUsageBinding? = null
    private val binding get() = _binding!!
    private val detailDataUsageViewModel: DetailsDataUsageViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance(mDataUsage: DataUsage) =
            DetailsDataUsageFragment().apply {
                this.dataUsage = mDataUsage
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsDataUsageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataUsage().apply {
            detailDataUsageViewModel.selectedDataUsage.postValue(this)
            binding.yearTextView.text = "YEAR ${year}"
            val usageItems = quarterlyUsages.map {
                UsageItem(it.quarter, it.usage, totalUsage)
            }.toMutableList()
            usageItems.add(UsageItem("Total", totalUsage, totalUsage))
            binding.recyclerView.apply {
                adapter = QuarterlyUsageAdapter(usageItems)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun getDataUsage() : DataUsage {
        return detailDataUsageViewModel.selectedDataUsage.value ?: dataUsage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}