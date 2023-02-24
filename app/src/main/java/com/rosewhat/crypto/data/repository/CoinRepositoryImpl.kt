package com.rosewhat.crypto.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.rosewhat.crypto.data.database.CoinInfoDao
import com.rosewhat.crypto.data.mapper.CoinMapper
import com.rosewhat.crypto.data.workers.RefreshWorkerData
import com.rosewhat.crypto.domain.model.CoinInfo
import com.rosewhat.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val application: Application
) : CoinRepository {
    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshWorkerData.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshWorkerData.makeRequest()
        )
    }

}