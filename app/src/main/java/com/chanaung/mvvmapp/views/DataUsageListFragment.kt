package com.chanaung.mvvmapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chanaung.mvvmapp.databinding.FragmentDataUsageListBinding
import com.chanaung.mvvmapp.viewmodels.DataUsageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataUsageListFragment : Fragment() {

    private val dataUsageViewModel: DataUsageViewModel by viewModel()
    private var _binding: FragmentDataUsageListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDataUsageListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataUsageViewModel.dataUsage.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = DataUsageListAdapter(it)

        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)

        }
    }
}