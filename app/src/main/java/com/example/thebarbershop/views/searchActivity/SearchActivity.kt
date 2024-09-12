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
import com.example.thebarbershop.uiStates.BaseUiState
import com.example.thebarbershop.uiStates.SearchUiState
import com.example.thebarbershop.utils.UiUtils
import com.example.thebarbershop.views.BaseActivity
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
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root


        val contentFrame = findViewById<FrameLayout>(R.id.container)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val menu = bottomNavigationView.menu
        val secondMenuItem = menu.getItem(1)
        secondMenuItem.isChecked = true
        contentFrame.addView(view)

        binding.dateTv.text = UiUtils.getCurrentDate()

        lifecycleScope.launch {
            searchViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is BaseUiState.Loading -> {
                        //TODO : Show loading indicator
                    }

                    is BaseUiState.Success -> {
                        val searchData = uiState.data
                        binding.userNameTv.text = searchData.userEmail
                    }

                    is BaseUiState.Error -> {
                        //TODO : Show error message
                    }
                }
            }
        }
    }

    override fun highlightCurrentMenuItem() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_search
    }
}
