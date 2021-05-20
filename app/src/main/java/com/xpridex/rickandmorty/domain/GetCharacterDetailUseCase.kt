package com.xpridex.rickandmorty.domain

import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCase
@Inject
constructor(private val repository: RickAndMortyRepository) {
    fun execute(id: String): Flow<DomainCharacterItem> = repository.getCharacterDetail(id)
}