package com.xpridex.rickandmorty.domain

import com.xpridex.rickandmorty.data.RickAndMortyDataRepository
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import com.xpridex.rickandmorty.domain.model.DomainInfo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlinx.coroutines.flow.collect

class GetCharacterListUseCaseTest {
    private val repository = mockk<RickAndMortyDataRepository>()

    private val useCase = GetCharacterListUseCase(repository)

    private val domainCharacterList = DomainCharacterList(
        info = DomainInfo(1, 1, "", ""),
        results = listOf(DomainCharacterItem(1, "", "", "", ""))
    )

    @Test
    fun `when calls 'execute', then return DomainCharacterList`() = runBlocking {
        stubRepository(domainCharacterList)

        val flow = useCase.execute()

        flow.collect { result ->
            assertEquals(domainCharacterList, result)
        }

        coVerify { repository.getCharacterList() }

    }

    private fun stubRepository(domainCharacterList: DomainCharacterList) {
        coEvery { repository.getCharacterList() } returns flow { emit(domainCharacterList) }
    }
}