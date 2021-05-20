package com.xpridex.rickandmorty.presentation.splash

import com.xpridex.rickandmorty.core.mvi.events.MviEffect

sealed class SplashUiEffect : MviEffect {
    object NavigateToCharacterListUiEffect : SplashUiEffect()
}