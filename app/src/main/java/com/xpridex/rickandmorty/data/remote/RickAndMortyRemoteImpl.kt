package com.xpridex.rickandmorty.data.remote

import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterDetailResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterListResponse
import com.xpridex.rickandmorty.data.source.RickAndMortyRemote
import javax.inject.Inject

class RickAndMortyRemoteImpl @Inject constructor(private val restApi: RickAndMortyRestApi) :
    RickAndMortyRemote {

    override suspend fun getCharacterList(): RemoteCharacterListResponse  = restApi.getCharacterList()

    override suspend fun getCharacterDetail(id: String): RemoteCharacterDetailResponse  = restApi.getCharacterDetail(id)
}