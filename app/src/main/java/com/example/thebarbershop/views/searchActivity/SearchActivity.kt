package com.example.thebarbershop.views.searchActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thebarbershop.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
}}