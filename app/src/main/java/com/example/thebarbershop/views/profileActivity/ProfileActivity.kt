package com.example.thebarbershop.views.profileActivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityHomeBinding
import com.example.thebarbershop.databinding.ActivityProfileBinding
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.NewReservationActivity
import com.example.thebarbershop.views.homeActivity.AppointmentsAdapter
import com.example.thebarbershop.views.homeActivity.HomeViewModel
import com.example.thebarbershop.views.homeActivity.NextToYouAdapter
import com.example.thebarbershop.views.searchActivity.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    private lateinit var binding : ActivityProfileBinding
    private val profileOptionsProvider = ProfileOptionsProvider()
    private lateinit var profileOptionsAdapter: ProfileOptionsListAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        val contentFrame = findViewById<FrameLayout>(R.id.container)
        contentFrame.addView(view)
        highlightCurrentMenuItem()

        val profileOptionsList = profileOptionsProvider.getProfileOptions()
        profileOptionsAdapter = ProfileOptionsListAdapter(profileOptionsList)
        binding.profileRv.layoutManager = LinearLayoutManager(this)
        binding.profileRv.adapter = profileOptionsAdapter

        /*lifecycleScope.launch {
            homeViewModel.uiState.collect { uiState ->
                if (uiState.isLoading) {
                    //TODO : Show loading indicator
                } else if (uiState.errorMessage != null) {
                    //TODO : Show error message
                } else {
                    appointmentAdapter.updateData(uiState.appointments)
                    nexToYouBusinessAdapter.updateData(uiState.nextToYouBusiness)
                }
            }
        }*/


        // Set click listener on the adapter

    }

    override fun highlightCurrentMenuItem() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_user
    }
}