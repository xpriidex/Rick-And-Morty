package com.xpridex.rickandmorty.ui.navigator

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.xpridex.rickandmorty.ui.splash.SplashFragmentDirections
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun goToCharacterList(view: View) {
        val direction =
            SplashFragmentDirections.actionSlashFragmentToCharacterListFragment()
        safeNavigation(view, direction)
    }

    private fun safeNavigation(view: View, direction: NavDirections) {
        view.findNavController().currentDestination?.getAction(direction.actionId)
            ?.let { view.findNavController().navigate(direction) }
    }
}

