package com.berker.cryptoberker.presentation.coin_list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.berker.cryptoberker.R
import com.berker.cryptoberker.databinding.RvItemCoinBinding
import com.berker.cryptoberker.domain.model.Coin

class CoinsViewHolder(
    private val itemBinding: RvItemCoinBinding,
    clickPosition: ((Int) -> Unit)?,
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    init {
        itemView.setOnClickListener {
            clickPosition?.invoke(adapterPosition)
        }
    }

    fun bind(coin: Coin) {
        itemBinding.apply {
            tvHolder.text = root.resources.getString(R.string.coins_list_placeholder,
                coin.rank,
                coin.name,
                coin.symbol)
            tvIsActive.text = when (coin.isActive) {
                true -> "active"
                else -> "inactive"
            }
        }
    }
}