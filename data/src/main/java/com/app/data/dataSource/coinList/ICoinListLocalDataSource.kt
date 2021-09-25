package com.app.data.dataSource.coinList

import com.app.domain.entities.coinList.Coin
import com.app.domain.utils.ResponseState

interface ICoinListLocalDataSource {

    suspend fun insertListOfCoin(coinList: List<Coin>)

    suspend fun getListOfCoin(): ResponseState<List<Coin>>
}