package com.example.thebarbershop.views.profileActivity.profileOptions.myappointmentsActivity

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityMyaddressBinding
import com.example.thebarbershop.databinding.ActivityMyappointmentsBinding
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.homeActivity.HomeViewModel
import com.example.thebarbershop.views.profileActivity.ProfileOptionsListAdapter
import com.example.thebarbershop.views.profileActivity.ProfileOptionsProvider
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Locale

class MyAppointmentsActivity: BaseActivity() {
    private lateinit var binding: ActivityMyappointmentsBinding
    private val profileOptionsProvider = ProfileOptionsProvider()
    private lateinit var profileOptionsAdapter: ProfileOptionsListAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myappointments)

        val dateTextViewFrom: TextView = findViewById(R.id.dateTextViewFrom)
        val dateTextViewTo: TextView = findViewById(R.id.dateTextViewTo)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Histórico de marcações")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(java.util.Date())
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

    override fun onHomeSelected() {
        TODO("Not yet implemented")
    }
}


