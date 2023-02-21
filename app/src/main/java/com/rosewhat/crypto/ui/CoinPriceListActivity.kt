package com.rosewhat.crypto.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rosewhat.crypto.R
import com.rosewhat.crypto.data.models.CoinPriceInfo
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
        coinAdapter = CoinInfoAdapter(this)
        with(coinAdapter) {
            rvCoinPriceList.adapter = this
            onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                    startActivity(CoinDetailActivity.newIntent(context = this@CoinPriceListActivity, fromSymbol = coinPriceInfo.fromSymbol))
                }
            }
        }
        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.priceList.observe(this) {
            coinAdapter.coinInfoList = it
        }
    }


}