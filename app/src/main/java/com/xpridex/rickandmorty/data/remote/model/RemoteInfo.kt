package com.xpridex.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteInfo(
    @SerializedName("count")  val count: Int,
    @SerializedName("pages")  val pages: Int,
    @SerializedName("next")  val next: String?,
    @SerializedName("prev")  val prev: String?
)