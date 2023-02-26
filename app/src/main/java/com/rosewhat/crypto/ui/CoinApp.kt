package com.rosewhat.crypto.ui

import android.app.Application
import androidx.work.Configuration
import com.rosewhat.crypto.data.workers.CoinWorkerDataFactory
import com.rosewhat.crypto.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerDataFactory: CoinWorkerDataFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                workerDataFactory
            )
            .build()
    }

}