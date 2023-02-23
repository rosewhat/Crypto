package com.rosewhat.crypto.data.workers

import android.app.Application
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.rosewhat.crypto.data.database.AppDatabase
import com.rosewhat.crypto.data.mapper.CoinMapper
import com.rosewhat.crypto.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshWorkerData(
    application: Application,
    workerParameters: WorkerParameters
) : CoroutineWorker(application, workerParameters) {
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val mapper = CoinMapper()


    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopsCoinInfo()
                val fSyms = mapper.mapNameListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDto = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDto.map {
                    mapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
    companion object {
        const val NAME = "RefreshWorkerData"

        fun makeRequest() : OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshWorkerData>().build()
        }
    }
}