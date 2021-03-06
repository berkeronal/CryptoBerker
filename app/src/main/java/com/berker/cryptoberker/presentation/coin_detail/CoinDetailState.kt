package com.berker.cryptoberker.presentation.coin_detail

import com.berker.cryptoberker.domain.model.CoinDetail
import com.berker.cryptoberker.domain.model.CoinEvent

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
)
