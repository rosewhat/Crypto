package com.rosewhat.crypto.domain.usecases

import com.rosewhat.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getCoinInfoList()
}