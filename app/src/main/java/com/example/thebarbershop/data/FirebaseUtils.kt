package com.example.thebarbershop.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.BarberShop
import com.example.thebarbershop.models.Business
import com.example.thebarbershop.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FirebaseUtils @Inject
constructor() {
    private val db = Firebase.firestore

    suspend fun addBusineesToFirestore(business: Business): Boolean {
        return try {
            val businessMap = hashMapOf(
                "name" to business.name,
                "address" to business.address,
                "type" to business.type
            )
            db.collection("business").add(businessMap).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getBusinessFromFirestore(): List<Business> {
        return try {
            val result = db.collection("business").get().await()
            result.documents.mapNotNull { it.toObject(Business::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUsersFromFirestore(): List<User> {
        return try {
            val result = db.collection("users").get().await()
            result.documents.mapNotNull { it.toObject(User::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchUserByLastName(lastName: String): User? {
        return try {
            val users = getUsersFromFirestore()
            users.find { it.lastName == lastName }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getAppointmentsFromFirestore(): List<Appointment> {
        return try {
            val appointmentsCollection = db.collection("apointments")
            val snapshot = withContext(Dispatchers.IO) { appointmentsCollection.get().await() } // Use await()
            snapshot.documents.map { document ->
                val appointment = document.toObject(Appointment::class.java)!!
                appointment.id = document.id
                appointment
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addApointmentToFirestore(data: Appointment): Boolean {
        return try {
            val apointmentMap = hashMapOf(
                "barber" to data.barber,
                "date" to data.date,
                "time" to data.time,
                "reservationName" to data.reservationName,
                "user_id" to data.userId
            )
            db.collection("apointments").add(apointmentMap).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun clearPastApointmentsFromFirestore(): Boolean {
        return try {
            val apointmentList = getAppointmentsFromFirestore()
            val pastApointments = apointmentList.filter {
                val apointmentDate = LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE)
                apointmentDate.isBefore(LocalDate.now())
            }
            pastApointments.forEach { apointment ->
                db.collection("apointments").document(apointment.id).delete().await()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun addBarberShopToFirestore(barberShop: BarberShop) : Boolean {
        return try{
            val barberShopMap = hashMapOf(
                "name" to barberShop.name,
                "district" to barberShop.district,
                "location" to barberShop.location,
                "address" to barberShop.address
            )
            db.collection("users").add(barberShopMap).await()
            true
        }catch (e : Exception){
            false
        }
    }

    suspend fun getBarberShopsFromFirestore() : List<BarberShop>{
        return try {
            val result = db.collection("barbershop").get().await()
            result.documents.mapNotNull { it.toObject(BarberShop::class.java) }
        }catch (e:Exception){
            emptyList()
        }
    }

    suspend fun addUserToFirestore(user : User) : Boolean{
        return try {
            val userMap = hashMapOf(
                "firstname" to user.firstName,
                "lastname" to user.lastName,
                "age" to user.age,
                "phoneNumber" to user.phoneNumber
            )
            db.collection("users").add(userMap).await()
            true
        }catch (e : Exception){
            false
        }
    }
}
