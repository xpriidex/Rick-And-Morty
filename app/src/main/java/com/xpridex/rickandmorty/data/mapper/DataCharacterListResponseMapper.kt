package com.xpridex.rickandmorty.data.mapper

import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterDetailResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterListResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteInfo
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import com.xpridex.rickandmorty.domain.model.DomainInfo
import javax.inject.Inject

class DataCharacterListResponseMapper @Inject constructor() {

    fun RemoteCharacterListResponse.toDomain() = DomainCharacterList(
        info = info.toDomain(),
        results = results.map { it.toDomain() }
    )

    private fun RemoteInfo.toDomain() = DomainInfo(
        count = count,
        pages = pages,
        next = next.orEmpty(),
        prev = prev.orEmpty()
    )

    fun RemoteCharacterDetailResponse.toDomain() = DomainCharacterItem(
        id = id,
        name = name,
        species = species,
        status = status,
        image = image
    )
}