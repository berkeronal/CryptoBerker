package com.berker.cryptoberker.domain.use_case.get_coin

import com.berker.cryptoberker.common.Resource
import com.berker.cryptoberker.data.remote.dto.toCoinDetail
import com.berker.cryptoberker.domain.model.CoinDetail
import com.berker.cryptoberker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error,"))
        } catch (e: IOException) {
            emit(Resource.Error("Error while reaching servers. Check your internet"))
        }
    }
}