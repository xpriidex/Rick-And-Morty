package com.xpridex.rickandmorty.data.mapper

import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterDetailResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterListResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteInfo
import com.xpridex.rickandmorty.domain.model.DomainCharacterDetail
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.domain.model.DomainCharacterList
import com.xpridex.rickandmorty.domain.model.DomainInfo
import javax.inject.Inject

class DataResponseMapper @Inject constructor() {

    fun RemoteCharacterListResponse.toDomain() = DomainCharacterList(
        info = info.toDomain(),
        results = results.map { it.toDomainItem() }
    )

    private fun RemoteInfo.toDomain() = DomainInfo(
        count = count,
        pages = pages,
        next = next.orEmpty(),
        prev = prev.orEmpty()
    )

    fun RemoteCharacterDetailResponse.toDomainItem() = DomainCharacterItem(
        id = id,
        name = name,
        species = species,
        status = status,
        image = image
    )

    fun RemoteCharacterDetailResponse.toDomainDetail() = DomainCharacterDetail(
        created = created,
        gender = gender,
        id = id,
        image = image,
        name = name,
        species = species,
        status = status,
        type = type,
        url = url
    )
}