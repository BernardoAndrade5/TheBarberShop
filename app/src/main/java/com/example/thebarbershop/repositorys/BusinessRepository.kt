package com.example.thebarbershop.repositorys

import android.util.Log
import com.example.thebarbershop.data.FirebaseUtils
import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.Business

class BusinessRepository(private val firebaseUtils : FirebaseUtils) {
    private val businessList = mutableListOf<Business>()

    suspend fun addAppoitment(business: Business):Boolean{
        val result = firebaseUtils.addBusineesToFirestore(business)
        if(result){
            businessList.add(business)
        }
        return result
    }

    fun getAllBusiness() : List<Business>{
        Log.d("MYTAG", businessList.toList().toString())
        return businessList.toList()
    }

    suspend fun fetchBusinessFromFirestore() : List<Business>{
        val fetchedBusiness = firebaseUtils.getBusinessFromFirestore()
        businessList.clear()
        businessList.addAll(fetchedBusiness)
        return businessList.toList()
    }
}