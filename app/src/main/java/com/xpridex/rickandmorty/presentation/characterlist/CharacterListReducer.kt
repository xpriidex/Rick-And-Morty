package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.mvi.MviReducer
import com.xpridex.rickandmorty.core.mvi.UnsupportedReduceException
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListResult.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListResult.GetCharacterListResult.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUiState.*
import javax.inject.Inject

class CharacterListReducer @Inject constructor() :
    MviReducer<CharacterListUiState, CharacterListResult> {

    override fun CharacterListUiState.reduce(result: CharacterListResult): CharacterListUiState {
        return when (val previousState = this) {
            is DefaultUiState -> previousState reduce result
            is LoadingUiState -> previousState reduce result
            is SuccessUiState -> previousState reduce result
            is ErrorUiState -> previousState reduce result
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DefaultUiState.reduce(result: CharacterListResult): CharacterListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun LoadingUiState.reduce(result: CharacterListResult): CharacterListUiState {
        return when (result) {
            is Success -> SuccessUiState(result.results)
            is Error -> ErrorUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun SuccessUiState.reduce(result: CharacterListResult): CharacterListUiState {
        return when (result) {
            is NavigateToResult.GoToDetail -> this
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun ErrorUiState.reduce(result: CharacterListResult): CharacterListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }
}