package com.xpridex.rickandmorty.presentation.splash

import com.xpridex.rickandmorty.core.mvi.events.MviResult

sealed class SplashResult : MviResult {

    sealed class GetSplashResult : SplashResult() {
        object InProgress : SplashResult()
        object GoToCharacterList : SplashResult()
    }
}