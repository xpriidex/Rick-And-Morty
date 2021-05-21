package com.xpridex.rickandmorty.presentation.characterdetail

import com.xpridex.rickandmorty.core.mvi.events.MviUiState
import com.xpridex.rickandmorty.domain.model.DomainCharacterDetail
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem

sealed class CharacterDetailUiState : MviUiState {
    object DefaultUiState : CharacterDetailUiState()
    object LoadingUiState : CharacterDetailUiState()
    data class SuccessUiState(val character: DomainCharacterDetail) : CharacterDetailUiState()
    object ErrorUiState : CharacterDetailUiState()
}