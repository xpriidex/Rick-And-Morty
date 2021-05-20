package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.events.MviUserIntent

sealed class CharacterListUIntent : MviUserIntent {

    object InitialUIntent : CharacterListUIntent()
}