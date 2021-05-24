package com.xpridex.rickandmorty.data

import com.xpridex.rickandmorty.data.mapper.DataResponseMapper
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterDetailResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterListResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteInfo
import com.xpridex.rickandmorty.data.source.RickAndMortyRemote
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import com.xpridex.rickandmorty.domain.model.DomainInfo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class RickAndMortyDataRepositoryTest {
    private val remote = mockk<RickAndMortyRemote>()
    private val dataResponseMapper = mockk<DataResponseMapper>()

    private val repository = RickAndMortyDataRepository(remote, dataResponseMapper)

    private val remoteCharacterListResponse = RemoteCharacterListResponse(
        info = RemoteInfo(1, 1, "", ""),
        results = listOf(RemoteCharacterDetailResponse("", "", 1, "", "", "", "", "", ""))
    )

    private val domainCharacterList = DomainCharacterList(
        info = DomainInfo(1, 1, "", ""),
        results = listOf(DomainCharacterItem(1, "", "", "", ""))
    )

    @Test
    fun `given remote when getCharacterList then Completes`() = runBlocking {
        stubGetCharacterList(remoteCharacterListResponse)
        stubDataResponseMapper(remoteCharacterListResponse, domainCharacterList)

        val flow = repository.getCharacterList()

        flow.collect { result ->
            assertEquals(domainCharacterList, result)
        }

        coVerify { remote.getCharacterList() }
    }

    private fun stubGetCharacterList(remoteCharacterListResponse: RemoteCharacterListResponse) {
        coEvery { remote.getCharacterList() } returns remoteCharacterListResponse
    }

    private fun stubDataResponseMapper(
        remote: RemoteCharacterListResponse,
        domain: DomainCharacterList
    ) {
        every { with(dataResponseMapper) { remote.toDomain() } } returns domain
    }
}