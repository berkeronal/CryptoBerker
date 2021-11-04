package com.berker.cryptoberker.presentation.coin_list

import com.berker.cryptoberker.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = "",
)