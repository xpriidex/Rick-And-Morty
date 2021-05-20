package com.xpridex.rickandmorty.presentation.splash

import com.xpridex.rickandmorty.core.mvi.MviReducer
import com.xpridex.rickandmorty.core.mvi.UnsupportedReduceException
import com.xpridex.rickandmorty.presentation.splash.SplashResult.*
import com.xpridex.rickandmorty.presentation.splash.SplashUiState.*
import javax.inject.Inject

class SplashReducer @Inject constructor() :
    MviReducer<SplashUiState, SplashResult> {

    override fun SplashUiState.reduce(result: SplashResult): SplashUiState {
        return when (val previousState = this) {
            is DefaultUiState -> previousState reduce result
            is ShowMortyDancerUiState -> previousState reduce result
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DefaultUiState.reduce(result: SplashResult): SplashUiState {
        return when (result) {
            GetSplashResult.InProgress -> ShowMortyDancerUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun ShowMortyDancerUiState.reduce(result: SplashResult): SplashUiState {
        return when (result) {
            GetSplashResult.GoToCharacterList -> ShowMortyDancerUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }
}