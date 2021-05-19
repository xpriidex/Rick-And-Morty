package com.xpridex.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.xpridex.rickandmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var navHostFragment = NavHostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupActivity()
    }

    private fun setupActivity() {
        setUpNavigation()
        destinationManager()
    }

    private fun setUpNavigation() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navGraph = navHostFragment.navController.graph
        navHostFragment.navController.setGraph(navGraph, intent.extras)
        appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    private fun destinationManager() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.slashFragment -> supportActionBar?.hide()
                else -> {
                    supportActionBar?.show()
                }
            }
        }
    }
}