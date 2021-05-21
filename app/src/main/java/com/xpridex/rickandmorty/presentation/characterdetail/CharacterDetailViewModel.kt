package com.xpridex.rickandmorty.presentation.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.rickandmorty.core.mvi.MviPresentation
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailAction.*
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailUIntent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val reducer: CharacterDetailReducer,
    private val actionProcessorHolder: CharacterDetailProcessor
) : ViewModel(), MviPresentation<CharacterDetailUIntent, CharacterDetailUiState> {

    private val defaultUiState: CharacterDetailUiState = CharacterDetailUiState.DefaultUiState

    private val uiState = MutableStateFlow(defaultUiState)

    override fun processUserIntents(userIntents: Flow<CharacterDetailUIntent>) {
        userIntents
            .buffer()
            .flatMapMerge { userIntent ->
                actionProcessorHolder.actionProcessor(userIntent.toAction())
            }
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduce result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun CharacterDetailUIntent.toAction(): CharacterDetailAction {
        return when (this) {
            is InitialUIntent -> GetCharacterDetailAction(id)
        }
    }

    override fun uiStates(): StateFlow<CharacterDetailUiState> = uiState
}
