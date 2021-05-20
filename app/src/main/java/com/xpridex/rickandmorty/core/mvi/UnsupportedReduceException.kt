package com.xpridex.rickandmorty.core.mvi

import com.xpridex.rickandmorty.core.mvi.events.MviResult
import com.xpridex.rickandmorty.core.mvi.events.MviUiState

class UnsupportedReduceException(state: MviUiState, result: MviResult) :
    RuntimeException("Cannot reduce state: $state with result: $result")