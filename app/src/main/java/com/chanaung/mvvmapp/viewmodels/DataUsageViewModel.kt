package com.chanaung.mvvmapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.network.NoConnectionInterceptor
import com.chanaung.mvvmapp.network.models.DataUsageResponse
import com.chanaung.mvvmapp.repository.DataUsageUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DataUsageViewModel(private val dataUsageUseCase: DataUsageUseCase): ViewModel() {

    val dataUsage = MutableLiveData<List<DataUsage>>()
    val errorMessage = MutableLiveData<String?>()

    init {
        fetchDataUsage()
    }

    fun fetchDataUsage() {
        viewModelScope.launch {
            var dataUsages = mutableListOf<DataUsage>()
            dataUsageUseCase.getDataUsages().apply {
                dataUsages.addAll(this)
                dataUsage.postValue(dataUsages)
            }
            try {
                dataUsageUseCase.fetchDataUsages()
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