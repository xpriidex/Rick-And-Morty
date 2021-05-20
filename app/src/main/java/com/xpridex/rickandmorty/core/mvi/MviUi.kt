package com.xpridex.rickandmorty.core.mvi

import com.xpridex.rickandmorty.core.mvi.events.MviUiState
import com.xpridex.rickandmorty.core.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow

interface MviUi<TUserIntent: MviUserIntent, in TUiState: MviUiState> {

    fun userIntents(): Flow<TUserIntent>

    fun renderUiStates(uiState: TUiState)
}