package com.chanaung.mvvmapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.repository.DataUsageUseCase
import com.chanaung.mvvmapp.viewmodels.DataUsageViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class DataUsageViewModelTest : BaseTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var dataUsageViewModel: DataUsageViewModel

    @MockK
    lateinit var dataUsageUseCase: DataUsageUseCase

    val dispatcher = Dispatchers.Unconfined

    @Before
    fun start() {
        super.setUp()
        MockKAnnotations.init(this)
        startKoin {
            modules(testAppComponent(getMockServerUrl()))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testDataUsageViewModelExpectedValue() = runTest {
        dataUsageViewModel = DataUsageViewModel(dataUsageUseCase)
        var dataUsages = listOf(DataUsage(2023, 200.40, emptyList()), DataUsage(2023, 1203.40, emptyList()))

        coEvery { dataUsageUseCase.fetchDataUsages() } returns Unit
        coEvery { dataUsageUseCase.getDataUsages() } returns dataUsages

        dataUsageViewModel.fetchDataUsage()
        dataUsageViewModel.dataUsage.observeForever {  }
        dataUsageViewModel.errorMessage.observeForever {  }

        assert(dataUsageViewModel.errorMessage.value == null)
        assert(dataUsageViewModel.dataUsage.value != null)
        assert(dataUsageViewModel.dataUsage.value?.get(0)?.year == 2023)
        assert(dataUsageViewModel.dataUsage.value?.get(1)?.totalUsage != 2023.00)
    }

}