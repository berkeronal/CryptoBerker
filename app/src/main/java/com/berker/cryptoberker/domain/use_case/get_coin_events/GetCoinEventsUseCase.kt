package com.berker.cryptoberker.domain.use_case.get_coin_events

import com.berker.cryptoberker.common.Resource
import com.berker.cryptoberker.data.remote.dto.toCoinEvent
import com.berker.cryptoberker.domain.model.CoinEvent
import com.berker.cryptoberker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinEventsUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coinId: String): Flow<Resource<List<CoinEvent>>> = flow {
        try {
            emit(Resource.Loading())
            val events = repository.getCoinEventByCoinId(coinId).map { it.toCoinEvent() }.reversed()
            emit(Resource.Success(events))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error,"))
        } catch (e: IOException) {
            emit(Resource.Error("Error while reaching servers for Coin Events. Check your internet"))
        }   catch (e:Exception){
            emit(Resource.Error((e.localizedMessage ?: "Unexpected Error,")))
        }
    }
}