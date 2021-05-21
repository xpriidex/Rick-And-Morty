package com.xpridex.rickandmorty.presentation.characterdetail

import com.xpridex.rickandmorty.core.mvi.MviReducer
import com.xpridex.rickandmorty.core.mvi.UnsupportedReduceException
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailResult.GetCharacterDetailResult.*
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailUiState.*
import javax.inject.Inject

class CharacterDetailReducer @Inject constructor() :
    MviReducer<CharacterDetailUiState, CharacterDetailResult> {

    override fun CharacterDetailUiState.reduce(result: CharacterDetailResult): CharacterDetailUiState {
        return when (val previousState = this) {
            is DefaultUiState -> previousState reduce result
            is LoadingUiState -> previousState reduce result
            is SuccessUiState -> previousState reduce result
            is ErrorUiState -> previousState reduce result
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DefaultUiState.reduce(result: CharacterDetailResult): CharacterDetailUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun LoadingUiState.reduce(result: CharacterDetailResult): CharacterDetailUiState {
        return when (result) {
            is  Success -> SuccessUiState(result.result)
            is  Error -> ErrorUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun SuccessUiState.reduce(result: CharacterDetailResult): CharacterDetailUiState {
        throw UnsupportedReduceException(this, result)
    }

    private infix fun ErrorUiState.reduce(result: CharacterDetailResult): CharacterDetailUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }
}