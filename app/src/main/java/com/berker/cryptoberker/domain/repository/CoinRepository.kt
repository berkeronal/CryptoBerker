package com.berker.cryptoberker.domain.repository

import com.berker.cryptoberker.data.remote.dto.CoinDetailDto
import com.berker.cryptoberker.data.remote.dto.CoinDto
import com.berker.cryptoberker.data.remote.dto.CoinEventDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
    suspend fun getCoinEventByCoinId(coinId: String): List<CoinEventDto>
}