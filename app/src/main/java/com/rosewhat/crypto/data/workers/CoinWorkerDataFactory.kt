package com.rosewhat.crypto.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.rosewhat.crypto.data.database.CoinInfoDao
import com.rosewhat.crypto.data.mapper.CoinMapper
import com.rosewhat.crypto.data.network.ApiService
import javax.inject.Inject
import javax.inject.Provider

class CoinWorkerDataFactory @Inject constructor(
    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            RefreshWorkerData::class.qualifiedName -> {
                val childWorkerFactory = workerProviders[RefreshWorkerData::class.java]?.get()
                return childWorkerFactory?.create(appContext, workerParameters)
            }
            else -> null
        }
    }
}