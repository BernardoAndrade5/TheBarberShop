package com.example.thebarbershop.views.profileActivity.profileOptions.myaccountActivity

import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityMyaccountBinding
import com.example.thebarbershop.databinding.ActivityProfileBinding
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.homeActivity.HomeViewModel
import com.example.thebarbershop.views.profileActivity.ProfileOptionsListAdapter
import com.example.thebarbershop.views.profileActivity.ProfileOptionsProvider

class MyAccountActivity: BaseActivity() {
    private lateinit var binding : ActivityMyaccountBinding
    private val profileOptionsProvider = ProfileOptionsProvider()
    private lateinit var profileOptionsAdapter: ProfileOptionsListAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setTitle("Os meus dados")
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun onHomeSelected() {
        TODO("Not yet implemented")
    }


}