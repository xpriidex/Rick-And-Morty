package com.xpridex.rickandmorty.presentation.splash

import com.xpridex.rickandmorty.core.mvi.events.MviUserIntent

sealed class SplashUIntent : MviUserIntent {

    object InitialUIntent : SplashUIntent()
}