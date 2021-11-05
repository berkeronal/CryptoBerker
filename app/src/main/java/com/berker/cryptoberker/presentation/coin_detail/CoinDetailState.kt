package com.berker.cryptoberker.presentation.coin_detail

import com.berker.cryptoberker.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
)