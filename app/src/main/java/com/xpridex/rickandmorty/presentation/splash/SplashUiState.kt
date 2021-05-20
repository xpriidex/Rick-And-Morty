package com.xpridex.rickandmorty.presentation.splash

import com.xpridex.rickandmorty.core.mvi.events.MviUiState

sealed class SplashUiState : MviUiState {
    object DefaultUiState : SplashUiState()
    object ShowMortyDancerUiState : SplashUiState()
}