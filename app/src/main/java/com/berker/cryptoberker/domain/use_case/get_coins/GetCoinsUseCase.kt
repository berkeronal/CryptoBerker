package com.berker.cryptoberker.domain.use_case.get_coins

import com.berker.cryptoberker.common.Resource
import com.berker.cryptoberker.data.remote.dto.toCoin
import com.berker.cryptoberker.domain.model.Coin
import com.berker.cryptoberker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error,"))
        } catch (e: IOException) {
            emit(Resource.Error("Error while reaching servers. Check your internet"))
        }
    }
}