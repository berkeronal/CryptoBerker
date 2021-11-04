package com.berker.cryptoberker.data.remote

import com.berker.cryptoberker.data.remote.dto.CoinDetailDto
import com.berker.cryptoberker.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinpaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coin/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}