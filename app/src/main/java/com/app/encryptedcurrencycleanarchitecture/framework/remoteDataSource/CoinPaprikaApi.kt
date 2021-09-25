package com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource

import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDetailDto
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinID}")
    suspend fun getCoinDetailByID(@Path("coinID") coinID: String): CoinDetailDto
}