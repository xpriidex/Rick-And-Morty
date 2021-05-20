package com.xpridex.rickandmorty.data.mapper

import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterDetailResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterListResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteInfo
import com.xpridex.rickandmorty.domain.model.DomainCharacterDetail
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
        next = next,
        pages = pages,
        prev = prev
    )

    fun RemoteCharacterDetailResponse.toDomain() = DomainCharacterDetail(
        name = name,
        url = url
    )
}