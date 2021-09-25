package com.app.data.dataSource.coinList

import com.app.domain.entities.coinList.Coin
import com.app.domain.utils.ResponseState

interface ICoinListRemoteDataSource {

    suspend fun getCoinList(): ResponseState<List<Coin>>
}