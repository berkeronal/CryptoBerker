package com.berker.cryptoberker.presentation.coin_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.berker.cryptoberker.R
import com.berker.cryptoberker.common.BaseFragment
import com.berker.cryptoberker.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>() {
    private val viewModel: CoinDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun initUi() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collectLatest {
                binding.apply {
                    it.apply {
                        if (isLoading) {
                            ltLoadingAnimation.visibility = View.VISIBLE
                        } else {
                            ltLoadingAnimation.visibility = View.GONE
                        }
                        if (coin != null) {
                            tvCoinName.text = coin.name
                        }
                        if (error != "") {
                            showErrorDialog(error)
                        }
                    }
                }
            }

        }
        lifecycleScope.launchWhenStarted {
            viewModel.eventState.collectLatest {
                binding.apply {
                    it.apply {
                        if (events.isNotEmpty()) {
                            tvCoinEvents.text = events[0].name
                        }
                        if (error != "") {
                            showErrorDialog(error)
                        }
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CoinDetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun layoutId(): Int = R.layout.fragment_coin_detail
}