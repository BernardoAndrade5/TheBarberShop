package com.example.thebarbershop.Models

import java.sql.Time
import java.util.Date

data class Appointment(
    val id : String = "",
    val barber : String = "",
    val date : String = "",
    val time : String = "",
    val reservationName : String = ""
)
