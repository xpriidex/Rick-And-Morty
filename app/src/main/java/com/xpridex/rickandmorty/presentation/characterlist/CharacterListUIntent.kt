package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviUserIntent

sealed class CharacterListUIntent : MviUserIntent {

    object InitialUIntent : CharacterListUIntent()

    object RetrySeeCharacterListUIntent : CharacterListUIntent()

    data class SeeDetailUIntent(val id: Int) : CharacterListUIntent()
}