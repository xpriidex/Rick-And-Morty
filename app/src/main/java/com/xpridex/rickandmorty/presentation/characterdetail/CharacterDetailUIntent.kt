package com.xpridex.rickandmorty.presentation.characterdetail

import com.xpridex.rickandmorty.core.mvi.events.MviUserIntent

sealed class CharacterDetailUIntent : MviUserIntent {

    data class InitialUIntent(val id: Int) : CharacterDetailUIntent()

    data class RetrySeeCharacterDetailUIntent(val id: Int) : CharacterDetailUIntent()
}