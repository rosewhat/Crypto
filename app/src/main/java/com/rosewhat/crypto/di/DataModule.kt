package com.rosewhat.crypto.di

import android.app.Application
import com.rosewhat.crypto.data.database.AppDatabase
import com.rosewhat.crypto.data.database.CoinInfoDao
import com.rosewhat.crypto.data.network.ApiFactory
import com.rosewhat.crypto.data.network.ApiService
import com.rosewhat.crypto.data.repository.CoinRepositoryImpl
import com.rosewhat.crypto.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl) : CoinRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideCoinDao(application: Application) : CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService() : ApiService {
            return ApiFactory.apiService
        }
    }
}