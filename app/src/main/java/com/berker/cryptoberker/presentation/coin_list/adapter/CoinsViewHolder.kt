package com.berker.cryptoberker.presentation.coin_list.adapter

import android.text.SpannableStringBuilder
import androidx.core.text.color
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
            clickPosition?.invoke(bindingAdapterPosition)
        }
    }

    fun bind(coin: Coin) {
        itemBinding.apply {
            tvHolder.text = root.resources.getString(R.string.coins_list_placeholder,
                coin.rank,
                coin.name,
                coin.symbol)
            tvIsActive.text = when (coin.isActive) {
                true -> {
                    val ssb = SpannableStringBuilder()
                    ssb.color(getColor(R.color.green)) {
                        append("active")
                    }
                }
                else -> {
                    val ssb = SpannableStringBuilder()
                    ssb.color(getColor(R.color.red)) {
                        append("inactive")
                    }
                }
            }
        }
    }

    private fun getColor(id: Int): Int {
        return itemBinding.root.resources.getColor(id, null)
    }
}