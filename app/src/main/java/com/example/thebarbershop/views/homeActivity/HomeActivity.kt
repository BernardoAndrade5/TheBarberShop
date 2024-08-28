package com.example.thebarbershop.views.homeActivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
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
import com.example.thebarbershop.uiStates.HomeUiState
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.NavigationHandler
import com.example.thebarbershop.views.NewReservationActivity
import com.example.thebarbershop.views.loginActivity.LoginActivity
import com.example.thebarbershop.views.registerActivity.RegisterActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.thebarbershop.views.searchActivity.SearchActivity
import com.google.api.Distribution.BucketOptions.Linear
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var appointmentAdapter: AppointmentsAdapter
    private lateinit var nexToYouBusinessAdapter : NextToYouAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    companion object{
        lateinit var auth : FirebaseAuth
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        val contentFrame = findViewById<FrameLayout>(R.id.container)
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH) + 1
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        contentFrame.addView(view)

        highlightCurrentMenuItem()

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val dateFormat = SimpleDateFormat("EEEE, d 'de' MMM yyyy", Locale("pt", "PT"))
        val formattedDate = dateFormat.format(calendar.time)

        binding.dateTv.text = formattedDate

        appointmentAdapter = AppointmentsAdapter(mutableListOf())
        binding.appointmentsRv.layoutManager = LinearLayoutManager(this)
        binding.appointmentsRv.adapter = appointmentAdapter
        binding.dateTv.text = formattedDate;
        nexToYouBusinessAdapter = NextToYouAdapter(this, mutableListOf())
        binding.nextToYouRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.nextToYouRv.adapter = nexToYouBusinessAdapter


        lifecycleScope.launch {
            homeViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is HomeUiState.Loading ->{
                        //TODO : Show loading indicator
                    }
                    is HomeUiState.Success -> {
                        //TODO : Hide loading indicator
                        appointmentAdapter.updateData(uiState.appointment)
                        nexToYouBusinessAdapter.updateData(uiState.nexToYouBusiness)
                        binding.userNameTv.text = uiState.userEmail
                        binding.signInBtn.visibility = if (uiState.isAuthenticated) View.GONE else View.VISIBLE
                        binding.signOutBtn.visibility = if (uiState.isAuthenticated) View.VISIBLE else View.GONE
                    }

                    is HomeUiState.Error -> {
                        // Hide loading indicator
                        //TODO : Hide loading indicator

                        // Show error message
                        // TODO: Implement proper error handling
                    }
                }
            }
        }

        binding.newReservationButton.setOnClickListener{
            val intent = Intent(this, NewReservationActivity::class.java)
            startActivity(intent)
        }

        binding.signOutBtn.setOnClickListener {
            homeViewModel.logoutCurrentUser()
        }

        binding.signInBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.searchBarView.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    override fun highlightCurrentMenuItem() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_home
    }
}