package com.example.thebarbershop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.thebarbershop.Models.User
import com.example.thebarbershop.Utils.FirebaseUtils
import com.example.thebarbershop.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val firebaseUtils = FirebaseUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userToAdd = User("Francisco", "Conceiçao", 21)

        binding.apply {
            addUserButton.setOnClickListener {
                lifecycleScope.launch {
                    firebaseUtils.addUserToFirestore(userToAdd)
                }
            }

            getUserButton.setOnClickListener {
                lifecycleScope.launch {
                    val users = firebaseUtils.getUsersFromFirestore()
                    if (users.isNotEmpty()) {
                        val firstUser = users[0]
                        binding.firstName.text = firstUser.firstName
                        binding.lastName.text = firstUser.lastName
                        binding.age.text = firstUser.age.toString()
                    } else {
                        Log.d("FIREBASE", "No users found")
                    }
                }
            }
        }
    }
}