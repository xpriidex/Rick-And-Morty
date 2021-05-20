package com.xpridex.rickandmorty.core.mvi

import com.xpridex.rickandmorty.core.mvi.events.MviEffect

interface MviUiEffect<in TUiEffect : MviEffect> {
    fun handleEffect(uiEffect: TUiEffect)
}