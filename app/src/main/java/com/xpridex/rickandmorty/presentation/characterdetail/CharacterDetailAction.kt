package com.xpridex.rickandmorty.presentation.characterdetail

import com.xpridex.rickandmorty.core.mvi.events.MviAction

sealed class CharacterDetailAction : MviAction {

    data class GetCharacterDetailAction(val id: Int) : CharacterDetailAction()
}