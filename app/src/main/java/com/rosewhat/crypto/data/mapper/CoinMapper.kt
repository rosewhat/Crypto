package com.rosewhat.crypto.data.mapper

import com.google.gson.Gson
import com.rosewhat.crypto.data.database.CoinInfoDbModel
import com.rosewhat.crypto.data.network.models.CoinInfoDto
import com.rosewhat.crypto.data.network.models.CoinInfoJsonContainerDto
import com.rosewhat.crypto.data.network.models.CoinNameListDto
import com.rosewhat.crypto.domain.model.CoinInfo

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        highday = dto.highday,
        lowday = dto.lowday,
        lastMarket = dto.lastMarket,
        imageUrl = BASE_IMAGE_URL + dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(
        coinInfoJsonContainerDto: CoinInfoJsonContainerDto
    ): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = coinInfoJsonContainerDto.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNameListToString(namesListDto: CoinNameListDto): String {
        return namesListDto.names.map { it.coinName.name }.joinToString(",")

    }

    fun mapDbModelToEntity(dmModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dmModel.fromSymbol,
        toSymbol = dmModel.toSymbol,
        price = dmModel.price,
        lastUpdate = dmModel.lastUpdate,
        highday = dmModel.highday,
        lowday = dmModel.lowday,
        lastMarket = dmModel.lastMarket,
        imageUrl = dmModel.imageUrl
    )

    companion object {
        private const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }

}

