package com.rosewhat.crypto.domain.usecases

import com.rosewhat.crypto.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getCoinInfoList()
}