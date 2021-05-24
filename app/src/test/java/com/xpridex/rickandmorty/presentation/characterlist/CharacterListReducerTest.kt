package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.presentation.characterlist.CharacterListResult.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUiState.*
import org.junit.Test

class CharacterListReducerTest{
    private val sutReducer = CharacterListReducer()

    @Test
    fun `given DefaultUiState with GetCharacterListResult-InProgress, when reduce, then return LoadingUiState`() {
        val previousState = DefaultUiState
        val result = GetCharacterListResult.InProgress

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is LoadingUiState)
    }

    @Test
    fun `given LoadingUiState with GetCharacterListResult-Success, when reduce, then return SuccessUiState`() {
        val previousState = LoadingUiState
        val result = GetCharacterListResult.Success(listOf())

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is SuccessUiState)
    }

    @Test
    fun `given LoadingUiState with GetCharacterListResult-Error, when reduce, then return ErrorUiState`() {
        val previousState = LoadingUiState
        val result = GetCharacterListResult.Error

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is ErrorUiState)
    }

    @Test
    fun `given SuccessUiState with NavigateToResult-GoToDetail, when reduce, then return SuccessUiState`() {
        val previousState = SuccessUiState(listOf())
        val result = NavigateToResult.GoToDetail(2)

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is SuccessUiState)
    }

    @Test
    fun `given ErrorUiState with GetCharacterListResult-InProgress, when reduce, then return LoadingUiState`() {
        val previousState = ErrorUiState
        val result = GetCharacterListResult.InProgress

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is LoadingUiState)
    }
}