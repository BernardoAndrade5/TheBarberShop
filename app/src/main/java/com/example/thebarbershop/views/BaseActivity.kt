package com.example.thebarbershop.views

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityBaseBinding
import com.example.thebarbershop.views.appointmentsActivity.AppointmentActivity
import com.example.thebarbershop.views.homeActivity.HomeActivity
import com.example.thebarbershop.views.profileActivity.ProfileActivity
import com.example.thebarbershop.views.searchActivity.SearchActivity
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
                    if(!isCurrentActivity(HomeActivity::class.java)){
                        navigationHandler.navigateToHome()
                        overridePendingTransition(0,0)
                    }
                    true
                }

                R.id.navigation_search -> {
                    if(!isCurrentActivity(SearchActivity::class.java)){
                        navigationHandler.navigateToSearch()
                        overridePendingTransition(0,0)
                    }
                    true
                }

                R.id.navigation_appointments -> {
                    if(!isCurrentActivity(AppointmentActivity::class.java)){
                        navigationHandler.navigateToAppoitnments()
                        overridePendingTransition(0,0)
                    }
                    true
                }

                R.id.navigation_user -> {
                    if(!isCurrentActivity(ProfileActivity::class.java)){
                        navigationHandler.navigateToProfile()
                        overridePendingTransition(0,0)
                    }
                    true
                }

                else -> false
            }
        }
    }

    abstract fun highlightCurrentMenuItem()

    private fun isCurrentActivity(activityClass: Class<out Activity>): Boolean {
        return this.javaClass.simpleName == activityClass.simpleName
    }
}