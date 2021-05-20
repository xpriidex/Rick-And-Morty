package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviEffect

sealed class CharacterListUiEffect : MviEffect {
    data class NavigateToCharacterDetailUiEffect(val id: Int) : CharacterListUiEffect()
}