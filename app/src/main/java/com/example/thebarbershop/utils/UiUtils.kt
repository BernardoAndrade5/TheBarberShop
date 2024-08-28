package com.example.thebarbershop.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Locale

object UiUtils {

    fun showDatePickerDialog(context : Context, editText: EditText){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day  = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                editText.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    fun showTimePickerDialog(context: Context, editText: EditText){
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour,selectedMinute)
                editText.setText(formattedTime)
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    fun getCurrentDate() : String{
        val currentDate = java.util.Calendar.getInstance()
        val year = currentDate.get(java.util.Calendar.YEAR)
        val month = currentDate.get(java.util.Calendar.MONTH)
        val day = currentDate.get(java.util.Calendar.DAY_OF_MONTH)
        val calendar = java.util.Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("EEEE, d 'de' MMM yyyy", Locale("pt", "PT"))
        val formattedDate = dateFormat.format(calendar.time)
        return formattedDate
    }
}