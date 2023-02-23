package com.rosewhat.crypto.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.rosewhat.crypto.data.network.ApiFactory
import com.rosewhat.crypto.data.database.AppDatabase
import com.rosewhat.crypto.data.network.models.CoinInfoDto
import com.rosewhat.crypto.data.network.models.CoinInfoJsonContainerDto
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {


    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    init {
        loadData()
    }


    private fun loadData() {
        val dispose = ApiFactory.apiService.getTopsCoinInfo()
            .map { it.names.map { it.coinName.name }.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            //бесконечно выполнять загрузку - пока успешно
            .repeat()
            // выполнит загрузку если все прошло не успешно
            .retry()

            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
            }, {
                Log.d(RESULT_PARSING_ERROR, it.toString())
            })
        compositeDisposable.add(dispose)
    }


    private fun getPriceListFromRawData(
        coinInfoJsonContainerDto: CoinInfoJsonContainerDto
    ): List<CoinInfoDto> {
        val result = ArrayList<CoinInfoDto>()
        val jsonObject = coinInfoJsonContainerDto.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        private const val RESULT_PARSING_POSITIVE = "TEST_LOADING"
        private const val RESULT_PARSING_ERROR = "TEST_LOADING_ERROR"
    }
}