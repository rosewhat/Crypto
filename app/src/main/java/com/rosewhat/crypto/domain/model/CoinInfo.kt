package com.rosewhat.crypto.domain.model

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String,
    val price: Double,
    val lastUpdate: Int,
    val highday: Double,
    val lowday: Double,
    val lastMarket: String,
    val imageUrl: String,
)