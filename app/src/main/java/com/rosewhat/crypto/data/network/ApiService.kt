package com.rosewhat.crypto.data.network

import com.rosewhat.crypto.data.network.models.CoinNameListDto
import com.rosewhat.crypto.data.network.models.CoinInfoJsonContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
   suspend fun getTopsCoinInfo(
        @Query(QEURY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNameListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QEURY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOL) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
    ): CoinInfoJsonContainerDto


    companion object {
        private const val QEURY_PARAM_API_KEY = "api_key"

        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"

        private const val QUERY_PARAM_FROM_SYMBOL = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val CURRENCY = "USD"
        private const val LIMIT = 10
        private const val API_KEY =
            "8869afe79f312ced8a7148b3d91a309d72e646305da17d47d5ab77dde6b3cb60"


    }
}
