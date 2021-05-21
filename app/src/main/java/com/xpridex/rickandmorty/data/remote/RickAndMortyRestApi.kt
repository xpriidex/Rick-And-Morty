package com.xpridex.rickandmorty.data.remote

import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterDetailResponse
import com.xpridex.rickandmorty.data.remote.model.RemoteCharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyRestApi {

    @GET("character")
    suspend fun getCharacterList() : RemoteCharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): RemoteCharacterDetailResponse
}