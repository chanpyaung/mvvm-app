package com.chanaung.mvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.chanaung.mvvmapp.viewmodels.DataUsageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dataUsageViewModel: DataUsageViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataUsageViewModel.dataUsage.observe(this) { dataUsage ->
            Log.d("MainActivity", dataUsage.year.toString())
        }
    }
}