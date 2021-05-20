package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviAction

sealed class CharacterListAction : MviAction {

    object GetCharacterListAction : CharacterListAction()

    data class GoToDetailAction(val id: Int) : CharacterListAction()
}