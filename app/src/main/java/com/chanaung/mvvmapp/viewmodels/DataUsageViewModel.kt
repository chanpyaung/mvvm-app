package com.chanaung.mvvmapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.network.NoConnectionInterceptor
import com.chanaung.mvvmapp.repository.DataUsageRepository
import kotlinx.coroutines.launch

class DataUsageViewModel(private val dataUsageRepository: DataUsageRepository): ViewModel() {

    val dataUsage = MutableLiveData<List<DataUsage>>()
    val errorMessage = MutableLiveData<String?>()

    init {
        fetchDataUsage()
    }

    fun fetchDataUsage() {
        viewModelScope.launch {
            var dataUsages = mutableListOf<DataUsage>()
            dataUsageRepository.getDataUsages().apply {
                dataUsages.addAll(this)
                dataUsage.postValue(dataUsages.reversed())
            }
            try {
                dataUsageRepository.fetchDataUsages()
            } catch (e: Exception) {
                if (dataUsages.isEmpty()) {
                    errorMessage.postValue(if (e is NoConnectionInterceptor.NoConnectivityException) e.message else "Something went wrong!")
                } else {
                    dataUsage.postValue(dataUsages)
                    errorMessage.postValue(null)
                }
            }
        }
    }

}