package com.example.thebarbershop.views.changePasswordActivity
import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityChangepasswordsBinding
import com.example.thebarbershop.databinding.ActivityMyaccountBinding
import com.example.thebarbershop.databinding.ActivityProfileBinding
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.homeActivity.HomeViewModel
import com.example.thebarbershop.views.profileActivity.ProfileOptionsListAdapter
import com.example.thebarbershop.views.profileActivity.ProfileOptionsProvider

class ChangePasswordActivity: AppCompatActivity() {
    private lateinit var binding : ActivityChangepasswordsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangepasswordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setTitle("Senha de acesso")
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}