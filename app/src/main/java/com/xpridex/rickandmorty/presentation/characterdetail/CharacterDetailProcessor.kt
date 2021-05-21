package com.xpridex.rickandmorty.presentation.characterdetail

import com.xpridex.rickandmorty.core.execution.CoroutineExecutionThread
import com.xpridex.rickandmorty.domain.GetCharacterDetailUseCase
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailAction.*
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailResult.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterDetailProcessor @Inject constructor(
    private val useCase: GetCharacterDetailUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: CharacterDetailAction): Flow<CharacterDetailResult> =
        when (actions) {
            is GetCharacterDetailAction -> characterDetailActionProcessor(actions.id)
        }

    private fun characterDetailActionProcessor(id: Int): Flow<CharacterDetailResult> =
        useCase.execute(id)
            .map {
                GetCharacterDetailResult.Success(it) as CharacterDetailResult
            }.onStart {
                emit(GetCharacterDetailResult.InProgress)
            }.catch {
                emit(GetCharacterDetailResult.Error)
            }
            .flowOn(coroutineThreadProvider.ioThread())
}