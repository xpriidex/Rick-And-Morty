package com.xpridex.rickandmorty.domain.model

data class DomainInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)