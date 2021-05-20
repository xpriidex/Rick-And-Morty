package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviUiState
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem

sealed class CharacterListUiState : MviUiState {
    object DefaultUiState : CharacterListUiState()
    object LoadingUiState : CharacterListUiState()
    data class SuccessUiState(val characters: List<DomainCharacterItem>) : CharacterListUiState()
    object ErrorUiState : CharacterListUiState()
}