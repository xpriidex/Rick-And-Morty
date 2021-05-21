package com.xpridex.rickandmorty.data

import com.xpridex.rickandmorty.data.mapper.DataResponseMapper
import com.xpridex.rickandmorty.data.source.RickAndMortyRemote
import com.xpridex.rickandmorty.domain.model.DomainCharacterDetail
import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import com.xpridex.rickandmorty.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RickAndMortyDataRepository @Inject constructor(
    private val remote: RickAndMortyRemote,
    private val mapper: DataResponseMapper
) : RickAndMortyRepository {

    override fun getCharacterList(): Flow<DomainCharacterList> = flow {
        val characterList = with(mapper) {
            remote.getCharacterList().toDomain()
        }
        emit(characterList)
    }

    override fun getCharacterDetail(id: Int): Flow<DomainCharacterDetail> = flow {
        val characterDetail = with(mapper) {
            remote.getCharacterDetail(id).toDomainDetail()
        }
        emit(characterDetail)
    }
}