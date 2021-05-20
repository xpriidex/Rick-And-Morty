package com.xpridex.rickandmorty.core.mvi

import com.xpridex.rickandmorty.core.mvi.events.MviUiState
import com.xpridex.rickandmorty.core.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface MviPresentation<TUserIntent: MviUserIntent, TUiState: MviUiState> {

    fun processUserIntents(userIntents: Flow<TUserIntent>)

    fun uiStates(): StateFlow<TUiState>
}