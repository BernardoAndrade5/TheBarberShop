package com.example.thebarbershop.views.mysubscriptionsActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityMyaddressBinding
import com.example.thebarbershop.databinding.ActivityMyexpensesBinding
import com.example.thebarbershop.databinding.ActivitySubscriptionsBinding
import com.example.thebarbershop.views.BaseActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.type.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MySubscriptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubscriptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Subscrições")
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