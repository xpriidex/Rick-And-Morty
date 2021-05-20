package com.xpridex.rickandmorty.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.rickandmorty.core.mvi.MviPresentation
import com.xpridex.rickandmorty.core.mvi.MviPresentationEffect
import com.xpridex.rickandmorty.presentation.splash.SplashAction.*
import com.xpridex.rickandmorty.presentation.splash.SplashResult.*
import com.xpridex.rickandmorty.presentation.splash.SplashUIntent.*
import com.xpridex.rickandmorty.presentation.splash.SplashUiEffect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val reducer: SplashReducer,
    private val actionProcessorHolder: SplashProcessor
) : ViewModel(), MviPresentation<SplashUIntent, SplashUiState>,
    MviPresentationEffect<SplashUiEffect> {

    private val defaultUiState: SplashUiState = SplashUiState.DefaultUiState

    private val uiState = MutableStateFlow(defaultUiState)
    private val uiEffect: MutableSharedFlow<SplashUiEffect> = MutableSharedFlow()

    override fun processUserIntents(userIntents: Flow<SplashUIntent>) {
        userIntents
            .buffer()
            .flatMapMerge { userIntent ->
                actionProcessorHolder.actionProcessor(userIntent.toAction())
            }
            .handleEffect()
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduce result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun SplashUIntent.toAction(): SplashAction {
        return when (this) {
            InitialUIntent -> GoToCharacterListAction
        }
    }

    private fun Flow<SplashResult>.handleEffect(): Flow<SplashResult> {
        return onEach { change ->
            val event = when (change) {
                GetSplashResult.GoToCharacterList -> NavigateToCharacterListUiEffect
                else -> return@onEach
            }
            uiEffect.emit(event)
        }
    }

    override fun uiStates(): StateFlow<SplashUiState> = uiState
    override fun uiEffect(): SharedFlow<SplashUiEffect> = uiEffect.asSharedFlow()
}
