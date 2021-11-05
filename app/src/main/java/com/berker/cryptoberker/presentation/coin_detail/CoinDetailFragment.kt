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
                    if (it.isLoading) {
                        ltLoadingAnimation.visibility = View.VISIBLE
                    } else {
                        ltLoadingAnimation.visibility = View.GONE
                    }
                    if (it.coin != null) {
                        tvCoinName.text = it.coin.name
                    }
                    if (it.error != "") {
                        showDialog(
                            "ERROR LOADING CONTENT",
                            "Error while loading content, try again later. ${it.error}"
                        )
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