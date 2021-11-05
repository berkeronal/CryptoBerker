package com.berker.cryptoberker.presentation.coin_detail

import com.berker.cryptoberker.domain.model.CoinEvent

data class CoinDetailEventState(
    val isLoading: Boolean = false,
    val error: String = "",
    val events: List<CoinEvent> = emptyList(),
)