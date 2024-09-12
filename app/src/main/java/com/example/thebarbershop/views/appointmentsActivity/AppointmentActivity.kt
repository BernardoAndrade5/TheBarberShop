package com.example.thebarbershop.views.appointmentsActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thebarbershop.R
import com.example.thebarbershop.views.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppointmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun highlightCurrentMenuItem() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_user
    }
}