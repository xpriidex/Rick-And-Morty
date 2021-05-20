package com.xpridex.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteInfo(
    @SerializedName("count")  val count: Int,
    @SerializedName("next")  val next: String,
    @SerializedName("pages")  val pages: Int,
    @SerializedName("prev")  val prev: Any
)