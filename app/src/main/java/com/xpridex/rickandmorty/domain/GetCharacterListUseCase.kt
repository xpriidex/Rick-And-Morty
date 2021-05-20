package com.xpridex.rickandmorty.domain

import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import com.xpridex.rickandmorty.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(private val repository: RickAndMortyRepository) {
    fun execute(): Flow<DomainCharacterList> = repository.getCharacterList()
}