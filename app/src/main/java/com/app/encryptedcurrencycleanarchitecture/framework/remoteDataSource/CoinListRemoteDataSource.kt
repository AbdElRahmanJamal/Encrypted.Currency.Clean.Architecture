package com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource

import com.app.data.dataSource.coinList.ICoinListRemoteDataSource
import com.app.domain.EntityMapper
import com.app.domain.entities.coinList.Coin
import com.app.domain.utils.ResponseState
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDto
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CoinListRemoteDataSource @Inject constructor(
        private val api: CoinPaprikaApi,
        private val mapper: EntityMapper<List<CoinDto>, List<Coin>>,
        dispatcher: CoroutineDispatcher
) : BaseRemoteDataSource<List<CoinDto>, List<Coin>>(dispatcher = dispatcher), ICoinListRemoteDataSource {

    override suspend fun startRemoteApiCall(apiParameter: HashMap<String, Any>?): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinList(): ResponseState<List<Coin>> {
        return getResponseState()
    }

    override fun onSuccess(apiResponse: List<CoinDto>): ResponseState<List<Coin>> {
        return ResponseState.Data(mapper.convert(apiResponse))
    }
}