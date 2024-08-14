package com.example.thebarbershop.views.homeActivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thebarbershop.data.FirebaseUtils
import com.example.thebarbershop.databinding.ActivityHomeBinding
import com.example.thebarbershop.repositorys.AppointmentRepository
import com.example.thebarbershop.viewModelFactory.HomeViewModelFactory
import com.example.thebarbershop.views.NewReservationActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var appointmentAdapter: AppointmentsAdapter
    private val firebaseUtils = FirebaseUtils()
    private val repository = AppointmentRepository(firebaseUtils)
    private val homeViewModel: HomeViewModel by viewModels(){
        HomeViewModelFactory(repository)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        appointmentAdapter = AppointmentsAdapter(mutableListOf())
        binding.appointmentsRv.layoutManager = LinearLayoutManager(applicationContext)
        binding.appointmentsRv.adapter = appointmentAdapter

        lifecycleScope.launch {
            homeViewModel.uiState.collect { uiState ->
                if (uiState.isLoading) {
                    //TODO : Show loading indicator
                } else if (uiState.errorMessage != null) {
                    //TODO : Show error message
                } else {
                    appointmentAdapter.updateData(uiState.appointments)
                }
            }
        }

        binding.newReservationButton.setOnClickListener{
            val intent = Intent(this, NewReservationActivity::class.java)
            startActivity(intent)
        }

        homeViewModel.loadAppointments()
    }
}