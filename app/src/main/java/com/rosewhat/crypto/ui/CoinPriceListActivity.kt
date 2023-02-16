package com.rosewhat.crypto.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rosewhat.crypto.R
import com.rosewhat.crypto.data.pojo.CoinPriceInfo
import com.rosewhat.crypto.ui.adapters.CoinInfoAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var coinAdapter: CoinInfoAdapter
    private val compositeDisposable = CompositeDisposable()
    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        viewModel.loadData()
        observeViewModel()
        coinAdapter = CoinInfoAdapter(this)
        with(coinAdapter) {
            rvCoinPriceList.adapter = this
            onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                    Log.d("ON_CLICK_INFO", coinPriceInfo.fromSymbol)
                }
            }
        }

    }

    private fun observeViewModel() {
        viewModel.priceList.observe(this) {
            Log.d("TEST_DATA", it.toString())
        }
    }


}