package com.example.thebarbershop.views.searchActivity

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.ActivityHomeBinding
import com.example.thebarbershop.databinding.ActivitySearchBinding
import com.example.thebarbershop.views.BaseActivity
import com.example.thebarbershop.views.homeActivity.HomeActivity.Companion.auth
import com.example.thebarbershop.views.homeActivity.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
@AndroidEntryPoint
class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set content view using binding
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root


        val contentFrame = findViewById<FrameLayout>(R.id.container)
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val menu = bottomNavigationView.menu
        val secondMenuItem = menu.getItem(1)
        secondMenuItem.isChecked = true
        contentFrame.addView(view)

        val dateFormat = SimpleDateFormat("EEEE, d 'de' MMM yyyy", Locale("pt", "PT"))
        val formattedDate = dateFormat.format(calendar.time)


        binding.dateTv.text = formattedDate

        lifecycleScope.launch {
            searchViewModel.uiState.collect { uiState ->
                if (uiState.isLoading) {
                    //TODO : Show loading indicator
                } else if (uiState.errorMessage != null) {
                    //TODO : Show error message
                } else {
                    binding.userNameTv.text = uiState.userEmail
                }
            }
        }
    }

    override fun onHomeSelected() {
    }

    override fun onResume() {
        super.onResume()
        // Optional: You can still set the email from ViewModel here if needed
        binding.userNameTv.text = searchViewModel.uiState.value.userEmail
    }
}
