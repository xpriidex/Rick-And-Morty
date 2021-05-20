package com.xpridex.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteCharacterDetailResponse(
    @SerializedName("created") val created: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)



