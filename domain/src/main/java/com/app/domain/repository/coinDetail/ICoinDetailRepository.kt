package com.app.domain.repository.coinDetail

import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.utils.ResponseState


interface ICoinDetailRepository {

    suspend fun getCoinDetailByID(coinID: String): ResponseState<CoinDetail>
}