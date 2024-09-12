package com.example.thebarbershop.repositorys

import android.util.Log
import com.example.thebarbershop.data.FirebaseUtils
import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.Business
import javax.inject.Inject

class BusinessRepository @Inject
constructor(private val firebaseUtils : FirebaseUtils) {
    private val businessList = mutableListOf<Business>()

    suspend fun addBusinessToFirestore(business: Business):Boolean{
        val result = firebaseUtils.addBusineesToFirestore(business)
        if(result){
            businessList.add(business)
        }
        return result
    }

    suspend fun fetchBusinessFromFirestore() : List<Business>{
        val fetchedBusiness = firebaseUtils.getBusinessFromFirestore()
        businessList.clear()
        businessList.addAll(fetchedBusiness)
        return businessList.toList()
    }
}