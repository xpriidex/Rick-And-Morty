package com.xpridex.rickandmorty.core.mvi

import com.xpridex.rickandmorty.core.mvi.events.MviResult
import com.xpridex.rickandmorty.core.mvi.events.MviUiState

interface MviReducer<TUiState : MviUiState, TResult: MviResult> {

    infix fun TUiState.reduce(result: TResult): TUiState
}