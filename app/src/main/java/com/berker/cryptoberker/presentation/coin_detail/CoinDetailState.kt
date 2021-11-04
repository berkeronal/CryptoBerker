package com.berker.cryptoberker.presentation.coin_detail

import com.berker.cryptoberker.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: String = "",
)