package com.chanaung.mvvmapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chanaung.mvvmapp.databinding.FragmentDataUsageListBinding
import com.chanaung.mvvmapp.viewmodels.DataUsageViewModel
import com.google.android.material.snackbar.Snackbar
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
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataUsageViewModel.dataUsage.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = DataUsageListAdapter(it, object : ViewItemClickListener {
                override fun onClick(position: Int) {
                    val bundle = bundleOf("selectedYear" to position)
                    DataUsageListFragmentDirections.actionDataUsageListFragmentToViewPagerFragment().setSelectedYear(position).let {
                        findNavController().navigate(it)
                    }
                }

            })

        }
        dataUsageViewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT)
                    .setAction("Try Again") {
                        dataUsageViewModel.fetchDataUsage()
                    }
                    .show()
            }
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}