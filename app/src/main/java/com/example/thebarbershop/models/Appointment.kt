package com.example.thebarbershop.models

import com.google.firebase.firestore.Exclude

data class Appointment(
    val barber : String = "",
    val date : String = "",
    val time : String = "",
    val reservationName : String = "",
    val userId : String = "",
    @Exclude var id : String = ""
)
