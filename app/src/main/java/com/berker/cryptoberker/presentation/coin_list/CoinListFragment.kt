package com.berker.cryptoberker.presentation.coin_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berker.cryptoberker.R
import com.berker.cryptoberker.common.BaseFragment
import com.berker.cryptoberker.common.Constants
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
                    binding.ltLoadingAnimation.visibility = View.VISIBLE
                } else {
                    binding.ltLoadingAnimation.visibility = View.GONE
                }
                if (it.coins.isNotEmpty()) {
                    initRecyclerView(it.coins)
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

    private fun initRecyclerView(itemList: List<Coin>) {
        val coinsAdapter = CoinsAdapter(itemList)
        coinsAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        coinsAdapter.setOnItemClickListener {
            val currentCoin = itemList[it]
            findNavController().navigate(
                R.id.action_coinListFragment_to_coinDetailFragment,
                bundleOf(Pair<String, String>(Constants.PARAM_COIN_ID, currentCoin.id)))
        }
        val recyclerView = binding.rvCoinList
        recyclerView.adapter = coinsAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun layoutId(): Int = R.layout.fragment_coin_list
}