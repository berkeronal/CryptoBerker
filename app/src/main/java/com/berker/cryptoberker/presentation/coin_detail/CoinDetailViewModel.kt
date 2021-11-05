package com.berker.cryptoberker.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berker.cryptoberker.common.Constants
import com.berker.cryptoberker.common.Resource
import com.berker.cryptoberker.domain.use_case.get_coin.GetCoinUseCase
import com.berker.cryptoberker.domain.use_case.get_coin_events.GetCoinEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val getCoinEventsUseCase: GetCoinEventsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state

    private val _eventState = MutableStateFlow(CoinDetailEventState())
    val eventState: StateFlow<CoinDetailEventState> = _eventState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
            getCoinEvent(coinId)
        }
    }

    private fun getCoinEvent(coinId: String) {
        getCoinEventsUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _eventState.value =
                        CoinDetailEventState(events = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _eventState.value = CoinDetailEventState(error = result.message ?: "Error occured")
                }
                is Resource.Loading -> {
                    _eventState.value = CoinDetailEventState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "Error occured")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}