package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviResult
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem

sealed class CharacterListResult : MviResult {

    sealed class GetCharacterListResult : CharacterListResult() {
        object InProgress : CharacterListResult()
        data class Success(val results: List<DomainCharacterItem>) : CharacterListResult()
        object Error : CharacterListResult()
    }

    sealed class NavigateToResult : CharacterListResult() {
        data class GoToDetail(val id: Int) : NavigateToResult()
    }
}