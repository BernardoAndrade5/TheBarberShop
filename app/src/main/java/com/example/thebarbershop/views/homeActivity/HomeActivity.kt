package com.example.thebarbershop.views.homeActivity

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
import com.example.thebarbershop.data.FirebaseUtils
import com.example.thebarbershop.databinding.ActivityHomeBinding
import com.example.thebarbershop.databinding.BottomNavigationBarBinding
import com.example.thebarbershop.repositorys.AppointmentRepository
import com.example.thebarbershop.repositorys.BusinessRepository
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.NavigationHandler
import com.example.thebarbershop.views.NewReservationActivity
import com.google.api.Distribution.BucketOptions.Linear
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var appointmentAdapter: AppointmentsAdapter
    private lateinit var nexToYouBusinessAdapter : NextToYouAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        val contentFrame = findViewById<FrameLayout>(R.id.container)
        contentFrame.addView(view)

        appointmentAdapter = AppointmentsAdapter(mutableListOf())
        binding.appointmentsRv.layoutManager = LinearLayoutManager(this)
        binding.appointmentsRv.adapter = appointmentAdapter

        nexToYouBusinessAdapter = NextToYouAdapter(this, mutableListOf())
        binding.nextToYouRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.nextToYouRv.adapter = nexToYouBusinessAdapter


        lifecycleScope.launch {
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
        }

        binding.newReservationButton.setOnClickListener{
            val intent = Intent(this, NewReservationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onHomeSelected() {
        TODO("Not yet implemented")
    }
}