package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.execution.CoroutineExecutionThread
import com.xpridex.rickandmorty.domain.GetCharacterListUseCase
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListAction.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListResult.*
import com.xpridex.rickandmorty.presentation.splash.SplashResult.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterListProcessor @Inject constructor(
    private val useCase: GetCharacterListUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: CharacterListAction): Flow<CharacterListResult> =
        when (actions) {
            is GetCharacterListAction -> getCharacterListActionProcessor()
        }

    private fun getCharacterListActionProcessor(): Flow<CharacterListResult> =
        useCase.execute()
            .map {
                GetCharacterListResult.Success(it.results) as CharacterListResult
            }.onStart {
                emit(GetCharacterListResult.InProgress)
            }.catch {
                it
                emit(GetCharacterListResult.Error)
            }
            .flowOn(coroutineThreadProvider.ioThread())
}