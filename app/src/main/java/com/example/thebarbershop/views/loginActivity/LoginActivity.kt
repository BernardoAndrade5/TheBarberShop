package com.example.thebarbershop.views.loginActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.thebarbershop.databinding.ActivityLoginBinding
import com.example.thebarbershop.uiStates.LoginUiState
import com.example.thebarbershop.views.homeActivity.HomeActivity
import com.example.thebarbershop.views.registerActivity.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val loginViewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                loginViewModel.login(email, password)
            }
        }


        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        lifecycleScope.launch {
            loginViewModel.uiState.collect{state ->
                when(state){
                    is LoginUiState.Idle -> {}
                    is LoginUiState.Loading -> {
                        //TODO : Show loading indicator
                    }
                    is LoginUiState.Success -> {
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    }
                    is LoginUiState.Error -> {
                        Toast.makeText(this@LoginActivity,"Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}