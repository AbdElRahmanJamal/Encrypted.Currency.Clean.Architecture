package com.app.data.dataSource.coinDetail

import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.utils.ResponseState

interface ICoinDetailRemoteDataSource {

    suspend fun getCoinDetailByID(coinID: String): ResponseState<CoinDetail>
}