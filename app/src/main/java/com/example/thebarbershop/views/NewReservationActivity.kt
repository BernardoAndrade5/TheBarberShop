package com.example.thebarbershop.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.data.FirebaseUtils
import com.example.thebarbershop.utils.UiUtils
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
            dateInput.setOnClickListener{
                UiUtils.showDatePickerDialog(this@NewReservationActivity, dateInput)
            }
            timeInput.setOnClickListener{
                UiUtils.showTimePickerDialog(this@NewReservationActivity, timeInput)
            }

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
                finish()
            }
        }
    }
}