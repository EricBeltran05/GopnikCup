package com.codefastly.gopnikcupapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.codefastly.gopnikcupapp.commons.bases.BaseActivity
import com.codefastly.gopnikcupapp.commons.service.AssettoServerUseCase
import com.codefastly.gopnikcupapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val testUseCase = AssettoServerUseCase()
        testUseCase.execute()
    }

    override fun configureUI() {
        showLoading()
    }

}