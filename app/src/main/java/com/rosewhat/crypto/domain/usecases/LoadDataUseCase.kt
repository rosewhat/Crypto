package com.rosewhat.crypto.domain.usecases

import com.rosewhat.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.loadData()
}