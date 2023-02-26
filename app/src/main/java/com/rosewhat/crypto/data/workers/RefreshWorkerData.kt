package com.rosewhat.crypto.data.workers

import android.content.Context
import androidx.work.*
import com.rosewhat.crypto.data.database.CoinInfoDao
import com.rosewhat.crypto.data.mapper.CoinMapper
import com.rosewhat.crypto.data.network.ApiService

import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshWorkerData (
    context: Context,
    workerParameters: WorkerParameters,
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService
) : CoroutineWorker(context, workerParameters) {

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

    class Factory @Inject constructor(
        private val mapper: CoinMapper,
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshWorkerData(context, workerParameters, mapper, coinInfoDao, apiService)
        }
    }
}