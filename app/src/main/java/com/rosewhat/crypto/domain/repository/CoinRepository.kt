package com.rosewhat.crypto.domain.repository

import androidx.lifecycle.LiveData
import com.rosewhat.crypto.domain.model.CoinInfo

interface CoinRepository {

    fun getCoinInfoList() : LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String) : LiveData<CoinInfo>

}