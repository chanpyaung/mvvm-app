package com.chanaung.mvvmapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.repository.DataUsageRepository
import kotlinx.coroutines.launch

class DataUsageViewModel(private val dataUsageRepository: DataUsageRepository): ViewModel() {

    val dataUsage = MutableLiveData<DataUsage>()

    init {
        fetchDataUsage()
    }

    private fun fetchDataUsage() {
        viewModelScope.launch {
            dataUsage.postValue(dataUsageRepository.getDataUsage())
        }
    }

}