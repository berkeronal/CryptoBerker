package com.berker.cryptoberker.data.remote

import com.berker.cryptoberker.data.remote.dto.CoinDetailDto
import com.berker.cryptoberker.data.remote.dto.CoinDto
import com.berker.cryptoberker.data.remote.dto.CoinEventDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinpaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

    @GET("v1/coins/{coinId}/events")
    suspend fun getCoinEventByCoinId(@Path("coinId") coinId: String): List<CoinEventDto>
}