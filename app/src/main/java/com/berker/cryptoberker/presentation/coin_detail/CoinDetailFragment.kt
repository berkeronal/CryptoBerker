package com.berker.cryptoberker.presentation.coin_detail

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setPadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.berker.cryptoberker.R
import com.berker.cryptoberker.common.BaseFragment
import com.berker.cryptoberker.databinding.FragmentCoinDetailBinding
import com.berker.cryptoberker.domain.model.CoinDetail
import com.berker.cryptoberker.domain.model.CoinEvent
import com.google.android.material.card.MaterialCardView
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
                            setVisibilityToVisible(ltLoadingAnimation)
                        } else {
                            setVisibilityToGone(ltLoadingAnimation)
                        }
                        if (coin != null) {
                            initCoin(coin)
                            coin.tags?.forEach {
                                flTagsHolder.addView(createTagView(it))
                            }
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
                            showEvents()
                            binding.apply {
                                btnExtend.setOnClickListener {
                                    extendEvents()
                                }
                            }
                            events.forEach {
                                llEventsHolder.addView(createEventView(it))
                            }
                        } else {
                            hideEvents()
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
    private fun initCoin(coin: CoinDetail) {
        binding.apply {
            coin.apply {
                tvCoinName.text = context?.getString(R.string.coins_list_placeholder,
                    coin.rank,
                    coin.name,
                    coin.symbol)
                tvCoinDescription.text =
                    root.resources.getString(R.string.coins_detail_description_placeholder,
                        description)
            }
        }
    }

    private fun createTagView(tagName: String): MaterialCardView {
        val cardView = MaterialCardView(context)
        cardView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            radius = 5f
            setCardBackgroundColor(context.getColor(R.color.transparent))
            strokeColor = context.getColor(R.color.green)
            strokeWidth = 2
        }
        val textView = TextView(context)
        textView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            gravity = View.TEXT_ALIGNMENT_CENTER
            setPadding(5)
            text = tagName
        }
        cardView.addView(textView)
        return cardView
    }

    private fun createEventView(event: CoinEvent): View {
        val textView = TextView(context)
        textView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            gravity = View.TEXT_ALIGNMENT_CENTER
            setPadding(5)
            text = event.name
        }
        return textView
    }

    private fun createTransition(duration: Long = 1000): Transition {
        val transition: Transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(0.5f)
        transition.duration = duration
        return transition
    }

    private fun setVisibilityToGone(view: View) {
        view.visibility = View.GONE
    }

    private fun setVisibilityToVisible(view: View) {
        view.visibility = View.VISIBLE
    }

    private fun hideEvents() {
        binding.apply {
            setVisibilityToGone(tvEvents)
            setVisibilityToGone(btnExtend)
            setVisibilityToGone(llEventsHolder)
        }
    }

    private fun showEvents() {
        binding.apply {
            setVisibilityToVisible(tvEvents)
            setVisibilityToVisible(btnExtend)
            setVisibilityToVisible(llEventsHolder)
        }
    }

    private fun extendEvents() {
        binding.apply {
            val set = ConstraintSet()
            set.clone(binding.clRoot)
            llEventsHolder.layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            llEventsHolder.apply {
                set.connect(id,
                    ConstraintSet.TOP,
                    tvEvents.id,
                    ConstraintSet.BOTTOM,
                    0)
                set.connect(id,
                    ConstraintSet.BOTTOM,
                    -1,
                    ConstraintSet.BOTTOM,
                    0)
            }
            TransitionManager.beginDelayedTransition(binding.clRoot, createTransition())
            set.applyTo(binding.clRoot)
        }
    }
}