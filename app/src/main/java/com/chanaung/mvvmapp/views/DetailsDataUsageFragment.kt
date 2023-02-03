package com.chanaung.mvvmapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chanaung.mvvmapp.R
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.databinding.FragmentDetailsDataUsageBinding

class DetailsDataUsageFragment : Fragment() {

    private lateinit var dataUsage: DataUsage
    private var _binding: FragmentDetailsDataUsageBinding? = null
    private val binding get() = _binding!!

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
        dataUsage.apply {
            binding.yearTextView.text = "YEAR ${dataUsage.year}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}