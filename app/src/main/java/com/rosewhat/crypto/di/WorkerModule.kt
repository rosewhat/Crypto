package com.rosewhat.crypto.di

import androidx.work.ListenableWorker
import com.rosewhat.crypto.data.workers.ChildWorkerFactory
import com.rosewhat.crypto.data.workers.RefreshWorkerData
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshWorkerData::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshWorkerData.Factory) : ChildWorkerFactory
}