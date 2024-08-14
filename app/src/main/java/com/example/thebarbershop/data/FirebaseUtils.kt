package com.example.thebarbershop.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FirebaseUtils {
    private val db = Firebase.firestore

    suspend fun addUserToFirestore(user: User) : Boolean {
        return try{
            val userMap = hashMapOf(
                "firstName" to user.firstName,
                "lastName" to user.lastName,
                "age" to user.age
            )
            db.collection("users").add(userMap).await()
            true
        }catch (e : Exception){
            false
        }
    }

    suspend fun getUsersFromFirestore() : List<User> {
        return try {
            val result = db.collection("users").get().await()
            result.documents.mapNotNull { it.toObject(User::class.java) }
        }catch (e:Exception){
            emptyList()
        }
    }

    suspend fun searchUserByLastName(lastName: String) : User?{
        return try{
            val users = getUsersFromFirestore()
            users.find { it.lastName == lastName }
        }catch (e:Exception){
            throw e
        }
    }

    suspend fun getApointmentsFromFirestore() : List<Appointment>{
        return try {
            val result = db.collection("apointments").get().await()
            result.documents.mapNotNull { it.toObject(Appointment::class.java) }
        }catch (e:Exception){
            emptyList()
        }
    }

    suspend fun addApointmentToFirestore(data : Appointment) : Boolean{
        return try {
            val apointmentMap = hashMapOf(
                "barber" to data.barber,
                "date" to data.date,
                "time" to data.time,
                "reservationName" to data.reservationName
            )
            db.collection("apointments").add(apointmentMap).await()
            true
        }catch (e:Exception){
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun clearPastApointmentsFromFirestore() : Boolean{
        return try {
            val apointmentList = getApointmentsFromFirestore()
            val pastApointments = apointmentList.filter {
                val apointmentDate = LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE)
                apointmentDate.isBefore(LocalDate.now())
            }
            pastApointments.forEach{apointment ->
                db.collection("apointments").document(apointment.id).delete().await()
            }
            true
        }catch (e:Exception){
            false
        }
    }
}
