package com.xpridex.rickandmorty.domain.model

data class DomainCharacterItem(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val image: String
)