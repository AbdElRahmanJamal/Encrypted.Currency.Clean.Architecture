package com.app.data.dataSource.coinDetail

import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.utils.ResponseState

interface ICoinDetailLocalDataSource {

    suspend fun saveCoinDetails(coinDetail: CoinDetail)

    suspend fun getCoinDetailByID(coinID: String): ResponseState<CoinDetail>
}