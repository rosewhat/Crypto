package com.rosewhat.crypto.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String,
    val price: Double,
    val lastUpdate: Int,
    val highday: Double,
    val lowday: Double,
    val lastMarket: String,
    val imageUrl: String,
)