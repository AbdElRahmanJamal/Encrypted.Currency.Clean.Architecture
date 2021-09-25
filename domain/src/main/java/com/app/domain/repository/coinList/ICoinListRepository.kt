package com.app.domain.repository.coinList

import com.app.domain.entities.coinList.Coin
import com.app.domain.utils.ResponseState

interface ICoinListRepository {

    suspend fun getCoinList(): ResponseState<List<Coin>>
}