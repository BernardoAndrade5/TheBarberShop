package com.example.thebarbershop.views.registerActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.thebarbershop.databinding.ActivityRegisterBinding
import com.example.thebarbershop.repositorys.AuthRepository
import com.example.thebarbershop.uiStates.RegisterUiState
import com.example.thebarbershop.views.homeActivity.HomeActivity
import com.example.thebarbershop.views.loginActivity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.checkerframework.common.returnsreceiver.qual.This

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val registerViewModel : RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            val firstName = binding.firstNameInput.text.toString()
            val lastName = binding.lastNameInput.text.toString()
            val age = binding.ageInput.text.toString()
            val phoneNumber = binding.phoneNumberInput.text.toString()
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                registerViewModel.register(email, password, firstName, lastName, age, phoneNumber)
            }
        }

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        lifecycleScope.launch {
            registerViewModel.uiState.collect(){state ->
                when(state){
                    is RegisterUiState.Idle -> {}
                    is RegisterUiState.Loading -> {
                        //TODO : Show Loading indicator
                    }
                    is RegisterUiState.Success -> {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    }
                    is RegisterUiState.Error -> {
                        Toast.makeText(this@RegisterActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}