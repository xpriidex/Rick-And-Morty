package com.xpridex.rickandmorty.presentation.splash

import com.xpridex.rickandmorty.core.mvi.events.MviAction

sealed class SplashAction : MviAction {

    object GoToCharacterListAction : SplashAction()
}