package com.xpridex.rickandmorty.domain.model

data class DomainInfo(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)