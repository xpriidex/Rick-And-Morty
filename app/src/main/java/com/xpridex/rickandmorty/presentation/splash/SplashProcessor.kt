package com.xpridex.rickandmorty.presentation.splash

import com.xpridex.rickandmorty.presentation.splash.SplashAction.*
import com.xpridex.rickandmorty.presentation.splash.SplashResult.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashProcessor @Inject constructor() {

    fun actionProcessor(actions: SplashAction): Flow<SplashResult> =
            when (actions) {
                is GoToCharacterListAction -> goToCharacterListActionProcessor()
            }

    private fun goToCharacterListActionProcessor(): Flow<SplashResult> = flow {
        emit(GetSplashResult.InProgress)
        delay(3000)
        emit(GetSplashResult.GoToCharacterList)
    }
}