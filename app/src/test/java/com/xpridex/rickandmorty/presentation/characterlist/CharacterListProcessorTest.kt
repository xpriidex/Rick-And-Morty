package com.xpridex.rickandmorty.presentation.characterlist

import com.xpridex.rickandmorty.core.execution.FakeCoroutineExecutionThread
import com.xpridex.rickandmorty.domain.GetCharacterListUseCase
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import com.xpridex.rickandmorty.domain.model.DomainInfo
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListAction.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListResult.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CharacterListProcessorTest {
    private val getCharacterListUseCase = mockk<GetCharacterListUseCase>(relaxed = true)

    private val processor =
        CharacterListProcessor(getCharacterListUseCase, FakeCoroutineExecutionThread())

    private val error = Throwable()

    private val stubDomainInfo = DomainInfo(1, 1, "", "")
    private val stubDomainCharacterItem = DomainCharacterItem(1, "", "", "", "")

    private val stubDomainCharacterList = DomainCharacterList(
        info = stubDomainInfo,
        results = listOf(stubDomainCharacterItem)
    )

    @Test
    fun `given CharacterListAction and use case with DomainCharacterList, when calls 'actionProcessor', then return GetCharacterListResult-Success`() =
        runBlocking {
            stubGetCharacterListUseCase(stubDomainCharacterList)

            val results = processor.actionProcessor(GetCharacterListAction).toList()

            assertEquals(results[0], GetCharacterListResult.InProgress)
            assertEquals(results[1], GetCharacterListResult.Success(stubDomainCharacterList.results))
        }

    @Test
    fun `given CharacterListAction and use case with error, when calls 'actionProcessor', then return GetCharacterListResult-Error`() = runBlocking {
        stubGetCharacterListUseCaseError()

        val results = processor.actionProcessor(GetCharacterListAction).toList()

        assertEquals(results[0], GetCharacterListResult.InProgress)
        assertEquals(results[1], GetCharacterListResult.Error)
    }

    @Test
    fun `given GoToDetailAction, when calls 'actionProcessor', then return NavigateToResult-GoToDetail`() = runBlocking {
        val results = processor.actionProcessor(GoToDetailAction(2)).toList()

        assertEquals(results[0], NavigateToResult.GoToDetail(2))
    }

    private fun stubGetCharacterListUseCase(domainCharacterList: DomainCharacterList) {
        coEvery { getCharacterListUseCase.execute() } returns flow { emit(domainCharacterList) }
    }

    private fun stubGetCharacterListUseCaseError() {
        coEvery { getCharacterListUseCase.execute() } returns flow { throw error }
    }
}