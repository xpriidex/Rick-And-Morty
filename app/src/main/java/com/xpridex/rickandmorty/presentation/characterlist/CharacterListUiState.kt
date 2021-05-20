package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviUiState

sealed class CharacterListUiState : MviUiState {
    object DefaultUiState : CharacterListUiState()
    object LoadingUiState : CharacterListUiState()
    object SuccessUiState : CharacterListUiState()
    object ErrorUiState : CharacterListUiState()
}