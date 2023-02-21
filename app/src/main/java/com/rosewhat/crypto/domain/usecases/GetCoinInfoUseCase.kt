package com.rosewhat.crypto.domain.usecases

import com.rosewhat.crypto.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol = fromSymbol)
}