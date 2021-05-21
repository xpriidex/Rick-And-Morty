package com.xpridex.rickandmorty.data.source

import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterDetailResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterListResponse


interface RickAndMortyRemote {
    suspend fun getCharacterList(): RemoteCharacterListResponse
    suspend fun getCharacterDetail(id: Int): RemoteCharacterDetailResponse
}