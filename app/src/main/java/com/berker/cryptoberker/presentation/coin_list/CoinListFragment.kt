package com.berker.cryptoberker.presentation.coin_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.berker.cryptoberker.R
import com.berker.cryptoberker.common.BaseFragment
import com.berker.cryptoberker.databinding.FragmentCoinListBinding
import com.berker.cryptoberker.domain.model.Coin
import com.berker.cryptoberker.presentation.coin_list.adapter.CoinsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CoinListFragment : BaseFragment<FragmentCoinListBinding>() {
    private val viewModel: CoinListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CoinListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun initUi() {
        super.initUi()
        lifecycleScope.launchWhenCreated {
            viewModel.state.collectLatest {
                if (it.isLoading) {
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.loading.visibility = View.GONE
                }
                if (it.coins.isNotEmpty()) {
                    initRecyclerView(it.coins)
                }
            }
        }
    }

    private fun initRecyclerView(itemList: List<Coin>) {
        val coinsAdapter = CoinsAdapter(itemList)
        coinsAdapter.setOnItemClickListener {
        }
        val recyclerView = binding.rvCoinList
        recyclerView.adapter = coinsAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun layoutId(): Int = R.layout.fragment_coin_list
}