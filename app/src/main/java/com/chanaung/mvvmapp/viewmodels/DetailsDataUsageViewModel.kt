package com.chanaung.mvvmapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.repository.DataUsageRepository
import kotlinx.coroutines.launch

class DetailsDataUsageViewModel(private val dataUsageRepository: DataUsageRepository): ViewModel() {

    val selectedDataUsage = MutableLiveData<DataUsage>()
    val dataUsages = MutableLiveData<List<DataUsage>>()

    init {
        viewModelScope.launch {
            dataUsageRepository.getDataUsages().apply {
                dataUsages.postValue(this)
            }
        }
    }

}