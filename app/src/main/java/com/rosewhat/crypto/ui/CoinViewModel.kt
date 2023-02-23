package com.rosewhat.crypto.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rosewhat.crypto.data.repository.CoinRepositoryImpl
import com.rosewhat.crypto.domain.usecases.GetCoinInfoListUseCase
import com.rosewhat.crypto.domain.usecases.GetCoinInfoUseCase
import com.rosewhat.crypto.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch
import java.util.*


class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository = repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository = repository)
    private val loadDataUseCase = LoadDataUseCase(repository = repository)


    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        loadDataUseCase()
    }


}