package com.berker.cryptoberker.presentation

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.berker.cryptoberker.R
import com.berker.cryptoberker.presentation.coin_list.CoinListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(findViewById<FrameLayout>(R.id.fragmentHolder).id, CoinListFragment())
            .commit()
    }
}