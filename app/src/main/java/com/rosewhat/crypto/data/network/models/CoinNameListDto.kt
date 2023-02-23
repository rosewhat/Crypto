package com.rosewhat.crypto.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameListDto(
    @SerializedName("Data")
    @Expose
    val names: List<CoinNameContainerDto>,
)