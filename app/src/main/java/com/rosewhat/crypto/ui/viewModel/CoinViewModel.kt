package com.rosewhat.crypto.ui.viewModel

import androidx.lifecycle.ViewModel
import com.rosewhat.crypto.domain.usecases.GetCoinInfoListUseCase
import com.rosewhat.crypto.domain.usecases.GetCoinInfoUseCase
import com.rosewhat.crypto.domain.usecases.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {
    init {
        loadDataUseCase()
    }
    val coinInfoList = getCoinInfoListUseCase()
    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)
}