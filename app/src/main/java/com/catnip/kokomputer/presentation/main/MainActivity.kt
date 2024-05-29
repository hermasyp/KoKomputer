package com.catnip.kokomputer.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.catnip.kokomputer.R
import com.catnip.kokomputer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val isLogin = false

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.menu_tab_profile -> {
                    if (!isLogin) {
                        navigateToLogin()
                        controller.popBackStack(R.id.menu_tab_home, false)
                    }
                }
            }
        }
    }

    private fun navigateToLogin() {
        // startActivity(Intent(this, LoginActivity::class.java))
    }

    fun navigateToTabProfile() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.selectedItemId = R.id.menu_tab_profile
        navController.navigate(R.id.menu_tab_profile)
    }
}
