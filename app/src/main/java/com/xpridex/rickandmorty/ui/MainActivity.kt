package com.xpridex.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.xpridex.rickandmorty.R
import com.xpridex.rickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navGraph = navHostFragment.navController.graph
        navHostFragment.navController.setGraph(navGraph, intent.extras)
        appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.characterDetailFragment,
                R.id.characterListFragment
            )
            .build()

        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    private fun destinationManager() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.slashFragment -> supportActionBar?.hide()

                R.id.characterDetailFragment -> {
                    supportActionBar?.apply {
                        setDisplayHomeAsUpEnabled(true)
                        setDisplayShowHomeEnabled(true)
                        show()

                    }
                }
                else -> {
                    supportActionBar?.show()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}