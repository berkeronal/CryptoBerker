package com.berker.cryptoberker.domain.model

data class CoinEvent(
    val date: String,
    val dateTo: String?,
    val description: String,
    val id: String,
    val link: String,
    val name: String,
    val proofImageLink: String?,
)
