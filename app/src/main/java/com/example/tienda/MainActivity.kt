package com.example.tienda

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tienda.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setup()
    }

    private fun init() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setup() {
        setupAppBar()
        setupNavController()
        setupBottomNavigationView()
    }

    private fun setupAppBar() {
        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(setOf(
                R.id.productsListFragment,
                R.id.providersListFragment,
                R.id.transactionsFragment
            ))
        )
    }

    private fun setupNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.productsListFragment,
                R.id.providersListFragment,
                R.id.transactionsFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                else -> binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}