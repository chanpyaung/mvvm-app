package com.chanaung.mvvmapp.views

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.chanaung.mvvmapp.R
import com.chanaung.mvvmapp.databinding.ActivityMainBinding
import com.chanaung.mvvmapp.viewmodels.DataUsageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

//    val binding: ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
//        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }
}