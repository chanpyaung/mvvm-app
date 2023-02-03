package com.chanaung.mvvmapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.chanaung.mvvmapp.databinding.FragmentViewPagerBinding
import com.chanaung.mvvmapp.viewmodels.DetailsDataUsageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewPagerFragment : Fragment() {

    private val detailDataUsageViewModel: DetailsDataUsageViewModel by viewModel()
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private val args: ViewPagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailDataUsageViewModel.dataUsages.observe(viewLifecycleOwner) {
            val adapter = ViewPagerAdapter(it, fragmentManager = childFragmentManager, lifecycle)
            binding.pager.adapter = adapter
            binding.pager.offscreenPageLimit = 1
            binding.pager.currentItem = args.selectedYear
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}