package com.rosewhat.crypto.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rosewhat.crypto.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*
import kotlinx.android.synthetic.main.item_coin_info.*
import kotlinx.android.synthetic.main.item_coin_info.tvPrice

class CoinDetailActivity : AppCompatActivity() {


    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)


        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: "null"
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            tvPrice.text = it.price.toString()
            tvMinPrice.text = it.lowday.toString()
            tvMaxPrice.text = it.highday.toString()
            tvLastMarket.text = it.lastMarket
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
        }


    }


    companion object {
        private const val EXTRA_FROM_SYMBOL = "fsym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}