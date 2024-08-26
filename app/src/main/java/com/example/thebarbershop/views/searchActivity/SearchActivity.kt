package com.example.thebarbershop.views.searchActivity

import android.os.Bundle
import com.example.thebarbershop.R
import com.example.thebarbershop.views.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun highlightCurrentMenuItem() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_search
    }
}