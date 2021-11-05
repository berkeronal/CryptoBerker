package com.berker.cryptoberker.data.repository

import com.berker.cryptoberker.data.remote.CoinpaprikaApi
import com.berker.cryptoberker.data.remote.dto.CoinDetailDto
import com.berker.cryptoberker.data.remote.dto.CoinDto
import com.berker.cryptoberker.data.remote.dto.CoinEventDto
import com.berker.cryptoberker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinpaprikaApi,
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
    override suspend fun getCoinEventByCoinId(coinId: String): List<CoinEventDto> {
        return api.getCoinEventByCoinId(coinId)
    }
}