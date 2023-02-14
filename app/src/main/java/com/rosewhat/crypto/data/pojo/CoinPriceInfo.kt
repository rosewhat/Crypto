package com.rosewhat.crypto.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfo(
    @SerializedName("TYPE")
    @Expose
    val type: String,
    @SerializedName("MARKET")
    @Expose
    val market: String,
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String,
    @SerializedName("FLAGS")
    @Expose
    val flags: String,
    @SerializedName("PRICE")
    @Expose
    val price: Double,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Integer,
    @SerializedName("MEDIAN")
    @Expose
    val median: Double,
    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: Double,
    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastVolueto: Double,
    @SerializedName("LASTTRADEID")
    @Expose
    val lastTradeid: String,
    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: Double,
    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumeDayto: Double,
    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: Double,
    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24hourto: Double,
    @SerializedName("OPENDAY")
    @Expose
    val openday: Double,
    @SerializedName("HIGHDAY")
    @Expose
    val highday: Double,
    @SerializedName("LOWDAY")
    @Expose
    val lowday: Double,
    @SerializedName("OPEN24HOUR")
    @Expose
    val open24hour: Double,
    @SerializedName("HIGH24HOUR")
    @Expose
    val high24hour: Double,
    @SerializedName("LOW24HOUR")
    @Expose
    val low24hour: Double,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String,
    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumeHour: Double,
    @SerializedName("VOLUMEHOURTO")
    @Expose
    val volumeHourto: Double,
    @SerializedName("OPENHOUR")
    @Expose
    val openhour: Double,
    @SerializedName("HIGHHOUR")
    @Expose
    val highhour: Double,
    @SerializedName("LOWHOUR")
    @Expose
    val lowhour: Double,
    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    val toptiervolume24hour: Double,
    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    val toptiervolume24hourto: Double,
    @SerializedName("CHANGE24HOUR")
    @Expose
    val change24hour: Double,
    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    val changepct24hour: Double,
    @SerializedName("CHANGEDAY")
    @Expose
    val changeday: Double,
    @SerializedName("CHANGEPCTDAY")
    @Expose
    val changepctday: Double,
    @SerializedName("CHANGEHOUR")
    @Expose
    val changehour: Double,
    @SerializedName("CHANGEPCTHOUR")
    @Expose
    val changePcthour: Double,
    @SerializedName("CONVERSIONTYPE")
    @Expose
    val conversionType: String,
    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    val conversionsymbol: String,
    @SerializedName("SUPPLY")
    @Expose
    val supply: Integer,
    @SerializedName("MKTCAP")
    @Expose
    val mktcap: Double,
    @SerializedName("MKTCAPPENALTY")
    @Expose
    val mktcappenalty: Integer,
    @SerializedName("CIRCULATINGSUPPLY")
    @Expose
    val circulatingsupply: Integer,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    @Expose
    val circulatingsupplymktcap: Double,
    @SerializedName("TOTALVOLUME24H")
    @Expose
    val totalVolume24h: Double,
    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    val totalVolume24hto: Double,
    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    val totalToptierVolume24h: Double,
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    val totalToptierVolume24hto: Double,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String,
)