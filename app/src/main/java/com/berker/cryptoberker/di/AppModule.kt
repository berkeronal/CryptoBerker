package com.berker.cryptoberker.di

import com.berker.cryptoberker.common.Constants.BASE_URL
import com.berker.cryptoberker.data.remote.CoinpaprikaApi
import com.berker.cryptoberker.data.repository.CoinRepositoryImpl
import com.berker.cryptoberker.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): CoinpaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinpaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinpaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}