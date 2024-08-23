package com.example.thebarbershop.views

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Profile
import com.example.thebarbershop.views.appointmentsActivity.AppointmentActivity
import com.example.thebarbershop.views.homeActivity.HomeActivity
import com.example.thebarbershop.views.profileActivity.ProfileActivity
import com.example.thebarbershop.views.searchActivity.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationHandler (private var context : Context) {

    fun navigateToHome(){
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToSearch(){
        val intent = Intent(context, SearchActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToAppoitnments(){
        val intent = Intent(context, AppointmentActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToProfile(){
        val intent = Intent(context, ProfileActivity::class.java)
        context.startActivity(intent)
    }

}