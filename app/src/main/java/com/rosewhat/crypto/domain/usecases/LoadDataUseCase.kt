package com.rosewhat.crypto.domain.usecases

import com.rosewhat.crypto.domain.repository.CoinRepository

class LoadDataUseCase(private val repository: CoinRepository) {
     operator fun invoke() = repository.loadData()
}