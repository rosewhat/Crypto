package com.rosewhat.crypto.di

import androidx.lifecycle.ViewModel
import com.rosewhat.crypto.ui.viewModel.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(viewModel: CoinViewModel): ViewModel
}