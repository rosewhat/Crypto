package com.rosewhat.crypto.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rosewhat.crypto.R
import com.rosewhat.crypto.data.api.ApiFactory
import com.rosewhat.crypto.data.pojo.CoinPriceInfo
import com.rosewhat.crypto.ui.adapters.CoinInfoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var coinAdapter: CoinInfoAdapter
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        coinAdapter = CoinInfoAdapter(this)
        with(coinAdapter) {
            rvCoinPriceList.adapter = this
            onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                    Log.d("ON_CLICK_INFO", coinPriceInfo.fromSymbol)
                }
            }
        }
        val dispose = ApiFactory.apiService.getFullPriceList(fSyms = "BTC,ETH")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(RESULT_PARSING_POSITIVE, it.toString())
            }, {
                Log.d(RESULT_PARSING_ERROR, it.toString())
            })
        compositeDisposable.add(dispose)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        private const val RESULT_PARSING_POSITIVE = "TEST_LOADING"
        private const val RESULT_PARSING_ERROR = "TEST_LOADING_ERROR"

    }
}