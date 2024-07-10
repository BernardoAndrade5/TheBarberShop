package com.example.thebarbershop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.thebarbershop.Models.Appointment
import com.example.thebarbershop.Utils.FirebaseUtils
import com.example.thebarbershop.databinding.ActivityHomeBinding
import com.example.thebarbershop.databinding.ActivityNewReservationBinding
import kotlinx.coroutines.launch

class NewReservationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewReservationBinding
    private val firebase = FirebaseUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewReservationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            submitButton.setOnClickListener{
                Log.d("TEXTINPUT", barberInput.text.toString())
                val newAppointment = Appointment(
                    barberInput.text.toString(),
                    dateInput.text.toString(),
                    timeInput.text.toString(),
                    reservationNameInput.text.toString()
                )
                lifecycleScope.launch {
                    firebase.addApointmentToFirestore(newAppointment)
                }
            }
        }
    }
}