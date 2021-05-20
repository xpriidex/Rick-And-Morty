package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.execution.CoroutineExecutionThread
import com.xpridex.rickandmorty.domain.GetCharacterListUseCase
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListAction.GetCharacterListAction
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListAction.GoToDetailAction
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListResult.GetCharacterListResult
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListResult.NavigateToResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterListProcessor @Inject constructor(
    private val useCase: GetCharacterListUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: CharacterListAction): Flow<CharacterListResult> =
        when (actions) {
            is GetCharacterListAction -> getCharacterListActionProcessor()
            is GoToDetailAction -> goToDetailActionProcessor(actions.id)
        }

    private fun getCharacterListActionProcessor(): Flow<CharacterListResult> =
        useCase.execute()
            .map {
                GetCharacterListResult.Success(it.results) as CharacterListResult
            }.onStart {
                emit(GetCharacterListResult.InProgress)
            }.catch {
                emit(GetCharacterListResult.Error)
            }
            .flowOn(coroutineThreadProvider.ioThread())

    private fun goToDetailActionProcessor(id: Int): Flow<CharacterListResult> = flow {
        emit(NavigateToResult.GoToDetail(id))
    }
}