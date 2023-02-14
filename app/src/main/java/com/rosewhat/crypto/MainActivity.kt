package com.rosewhat.crypto

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rosewhat.crypto.data.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dispose = ApiFactory.apiService.getFullPriceList(fSyms = "BTC,ETH")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(RESULT_PARSING_POSITIVE , it.toString())
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