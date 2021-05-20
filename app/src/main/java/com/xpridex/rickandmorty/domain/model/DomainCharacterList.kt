package com.xpridex.rickandmorty.domain.model

data class DomainCharacterList(
    val info: DomainInfo,
    val results: List<DomainCharacterItem>
)