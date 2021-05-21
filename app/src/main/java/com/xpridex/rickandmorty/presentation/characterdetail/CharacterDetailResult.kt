package com.xpridex.rickandmorty.presentation.characterdetail

import com.xpridex.rickandmorty.core.mvi.events.MviResult
import com.xpridex.rickandmorty.domain.model.DomainCharacterDetail
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem

sealed class CharacterDetailResult : MviResult {

    sealed class GetCharacterDetailResult : CharacterDetailResult() {
        object InProgress : CharacterDetailResult()
        data class Success(val result: DomainCharacterDetail) : CharacterDetailResult()
        object Error : CharacterDetailResult()
    }
}