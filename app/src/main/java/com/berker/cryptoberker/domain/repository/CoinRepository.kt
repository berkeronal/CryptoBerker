package com.berker.cryptoberker.domain.repository

import com.berker.cryptoberker.data.remote.dto.CoinDetailDto
import com.berker.cryptoberker.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}