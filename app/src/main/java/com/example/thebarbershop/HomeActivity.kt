package com.example.thebarbershop

import AppointmentsAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thebarbershop.Utils.FirebaseUtils
import com.example.thebarbershop.databinding.ActivityHomeBinding
import com.example.thebarbershop.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val firebase = FirebaseUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launch {
            val appointmentAdapter = AppointmentsAdapter(firebase.getApointmentsFromFirestore())

            binding.appointmentsRv.apply {
                layoutManager = LinearLayoutManager(applicationContext)

                adapter = appointmentAdapter
            }
        }

        binding.newReservationButton.setOnClickListener{
            val intent = Intent(this, NewReservationActivity::class.java)
            startActivity(intent)
        }
    }
}