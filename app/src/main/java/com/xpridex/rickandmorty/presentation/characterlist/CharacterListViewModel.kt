package com.xpridex.rickandmorty.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.rickandmorty.core.mvi.MviPresentation
import com.xpridex.rickandmorty.core.mvi.MviPresentationEffect
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListAction.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUIntent.*

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val reducer: CharacterListReducer,
    private val actionProcessorHolder: CharacterListProcessor
) : ViewModel(), MviPresentation<CharacterListUIntent, CharacterListUiState>,
    MviPresentationEffect<CharacterListUiEffect> {

    private val defaultUiState: CharacterListUiState = CharacterListUiState.DefaultUiState

    private val uiState = MutableStateFlow(defaultUiState)
    private val uiEffect: MutableSharedFlow<CharacterListUiEffect> = MutableSharedFlow()

    override fun processUserIntents(userIntents: Flow<CharacterListUIntent>) {
        userIntents
            .buffer()
            .flatMapMerge { userIntent ->
                actionProcessorHolder.actionProcessor(userIntent.toAction())
            }
           // .handleEffect()
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduce result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun CharacterListUIntent.toAction(): CharacterListAction {
        return when (this) {
            InitialUIntent -> GetCharacterListAction
        }
    }

    //private fun Flow<CharacterListResult>.handleEffect(): Flow<CharacterListResult> {
    //    return onEach { change ->
    //        val event = when (change) {
    //            CharacterListResult.G -> NavigateToCharacterListUiEffect
    //            else -> return@onEach
    //        }
    //        uiEffect.emit(event)
    //    }
    //}

    override fun uiStates(): StateFlow<CharacterListUiState> = uiState
    override fun uiEffect(): SharedFlow<CharacterListUiEffect> = uiEffect.asSharedFlow()
}
