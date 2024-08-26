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
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.NavigationHandler
import com.example.thebarbershop.views.NewReservationActivity
import com.example.thebarbershop.views.loginActivity.LoginActivity
import com.example.thebarbershop.views.registerActivity.RegisterActivity
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

        handleSignInSignOutBtn()

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
                if (uiState.isLoading) {
                    //TODO : Show loading indicator
                } else if (uiState.errorMessage != null) {
                    //TODO : Show error message
                }else{
                    appointmentAdapter.updateData(uiState.appointments)
                    nexToYouBusinessAdapter.updateData(uiState.nextToYouBusiness)
                    binding.userNameTv.text = uiState.userEmail
                }
            }
        }

        binding.newReservationButton.setOnClickListener{
            val intent = Intent(this, NewReservationActivity::class.java)
            startActivity(intent)
        }

        binding.signOutBtn.setOnClickListener {
            homeViewModel.logoutCurrentUser()
            binding.userNameTv.text = updateData()
            handleSignInSignOutBtn()
        }

        binding.signInBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.searchBarView.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.userNameTv.text = updateData()
        handleSignInSignOutBtn()
    }

    private fun updateData() : String{
        return auth.currentUser?.email.toString()
    }

    private fun handleSignInSignOutBtn(){
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null){
            binding.signInBtn.visibility = View.VISIBLE
            binding.signOutBtn.visibility = View.GONE
        }else{
            binding.signInBtn.visibility = View.GONE
            binding.signOutBtn.visibility = View.VISIBLE
        }
    }


    override fun onHomeSelected() {
        TODO("Not yet implemented")
    }
}