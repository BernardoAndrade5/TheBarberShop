package com.example.thebarbershop.views.myappointmentsActivity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityMyappointmentsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Locale

class MyAppointmentsHistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMyappointmentsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyappointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        binding.toolbar.setTitle("Histórico de marcações")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(java.util.Date())

        binding.apply {
            dateTextViewFrom.text = currentDate
            dateTextViewTo.text = currentDate

            dateTextViewFrom.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()

                datePicker.show(supportFragmentManager, "datePicker")

                datePicker.addOnPositiveButtonClickListener {
                    val selectedDateInMillis = it
                    val selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                        java.util.Date(selectedDateInMillis)
                    )
                    dateTextViewFrom.text = selectedDate
                }
            }

            dateTextViewTo.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()

                datePicker.show(supportFragmentManager, "datePicker")

                datePicker.addOnPositiveButtonClickListener {
                    val selectedDateInMillis = it
                    val selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                        java.util.Date(selectedDateInMillis)
                    )
                    dateTextViewTo.text = selectedDate
                }
            }
        }
    }
}


