package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviResult
import com.xpridex.rickandmorty.domain.model.DomainCharacterDetail

sealed class CharacterListResult : MviResult {

    sealed class GetCharacterListResult : CharacterListResult() {
        object InProgress : CharacterListResult()
        data class Success(val results: List<DomainCharacterDetail>) : CharacterListResult()
        object Error : CharacterListResult()
    }
}