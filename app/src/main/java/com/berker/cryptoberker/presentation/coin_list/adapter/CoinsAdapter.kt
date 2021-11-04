package com.berker.cryptoberker.presentation.coin_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berker.cryptoberker.databinding.RvItemCoinBinding
import com.berker.cryptoberker.domain.model.Coin

class CoinsAdapter(private val itemList: List<Coin>) : RecyclerView.Adapter<CoinsViewHolder>() {
    private var itemClickLister: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val itemBinding =
            RvItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinsViewHolder(itemBinding,itemClickLister)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
    fun setOnItemClickListener(itemClickLister: ((Int) -> Unit)?) {
        this.itemClickLister = itemClickLister
    }
}