package com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource

import com.app.data.dataSource.coinDetail.ICoinDetailRemoteDataSource
import com.app.domain.EntityMapper
import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.utils.BundleKeys
import com.app.domain.utils.ResponseState
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDetailDto
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CoinDetailRemoteDataSource @Inject constructor(
        private val api: CoinPaprikaApi,
        private val mapper: EntityMapper<CoinDetailDto, CoinDetail>,
        dispatcher: CoroutineDispatcher
) : BaseRemoteDataSource<CoinDetailDto, CoinDetail>(dispatcher = dispatcher), ICoinDetailRemoteDataSource {

    override suspend fun startRemoteApiCall(apiParameter: HashMap<String, Any>?): CoinDetailDto {
        var coinID = ""
        apiParameter?.let {
            if (it.getValue(BundleKeys.COIN_ID_HASH_MAP_KEY) is String)
                coinID = it.getValue(BundleKeys.COIN_ID_HASH_MAP_KEY) as String
        }
        return api.getCoinDetailByID(coinID)
    }

    override suspend fun getCoinDetailByID(coinID: String): ResponseState<CoinDetail> {
        val apiKeys = HashMap<String, Any>()
        apiKeys[BundleKeys.COIN_ID_HASH_MAP_KEY] = coinID
        return getResponseState(apiKeys)
    }

    override fun onSuccess(apiResponse: CoinDetailDto): ResponseState<CoinDetail> {
        return ResponseState.Data(mapper.convert(apiResponse))
    }
}