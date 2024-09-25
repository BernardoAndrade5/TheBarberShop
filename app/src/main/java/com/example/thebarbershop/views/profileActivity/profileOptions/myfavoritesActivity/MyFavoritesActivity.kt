package com.example.thebarbershop.views.profileActivity.profileOptions.myfavoritesActivity

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityMyappointmentsBinding
import com.example.thebarbershop.databinding.ActivityMyfavoritesBinding
import com.example.thebarbershop.databinding.ActivityMypacackagesBinding
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.homeActivity.HomeViewModel
import com.example.thebarbershop.views.profileActivity.ProfileOptionsListAdapter
import com.example.thebarbershop.views.profileActivity.ProfileOptionsProvider
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Locale

class MyFavoritesActivity: BaseActivity() {
    private lateinit var binding: ActivityMyfavoritesBinding
    private val profileOptionsProvider = ProfileOptionsProvider()
    private lateinit var profileOptionsAdapter: ProfileOptionsListAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myfavorites)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Os meus favoritos")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }

    override fun onHomeSelected() {
        TODO("Not yet implemented")
    }
}