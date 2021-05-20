package com.xpridex.rickandmorty.domain.repository

import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    fun getCharacterList(): Flow<DomainCharacterList>
    fun getCharacterDetail(id: String): Flow<DomainCharacterItem>
}
