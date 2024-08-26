package com.example.thebarbershop.views

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityBaseBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        navigationHandler = NavigationHandler(this)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    onHomeSelected()
                    true
                }

                R.id.navigation_search -> {
                    navigationHandler.navigateToSearch()
                    true
                }

                R.id.navigation_appointments -> {
                    navigationHandler.navigateToAppoitnments()
                    true
                }

                R.id.navigation_user -> {
                    navigationHandler.navigateToProfile()
                    true
                }

                else -> false
            }
        }
    }

    abstract fun onHomeSelected()

}