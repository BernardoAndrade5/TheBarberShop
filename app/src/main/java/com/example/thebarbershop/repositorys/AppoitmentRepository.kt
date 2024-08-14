package com.example.thebarbershop.repositorys

import android.util.Log
import com.example.thebarbershop.data.FirebaseUtils
import com.example.thebarbershop.models.Appointment

class AppointmentRepository(private val firebaseUtils : FirebaseUtils) {
    private val appointments = mutableListOf<Appointment>()

    suspend fun addAppoitment(appoitment: Appointment):Boolean{
        val result = firebaseUtils.addApointmentToFirestore(appoitment)
        if(result){
            appointments.add(appoitment)
        }
        return result
    }

    fun getAllAppoitments() : List<Appointment>{
        Log.d("MYTAG", appointments.toList().toString())
        return appointments.toList()
    }

    suspend fun fetchAppoitmentsFromFirestore() : List<Appointment>{
        val fetchedAppoitments = firebaseUtils.getApointmentsFromFirestore()
        appointments.clear()
        appointments.addAll(fetchedAppoitments)
        return appointments.toList()
    }
}