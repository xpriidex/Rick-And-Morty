package com.xpridex.rickandmorty.data.remote.model

data class RemoteCharacterListResponse(
    val info: RemoteInfo,
    val results: List<RemoteCharacterDetailResponse>
)