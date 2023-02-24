package com.rosewhat.crypto.domain.usecases

import com.rosewhat.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol = fromSymbol)
}