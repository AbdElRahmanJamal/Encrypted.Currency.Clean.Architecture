package com.app.data.repository.coinDetail

import com.app.data.dataSource.coinDetail.ICoinDetailLocalDataSource
import com.app.data.dataSource.coinDetail.ICoinDetailRemoteDataSource
import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.repository.coinDetail.ICoinDetailRepository
import com.app.domain.utils.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class CoinDetailRepository @Inject constructor(
    private val remoteDataSource: ICoinDetailRemoteDataSource,
    private val localDataSource: ICoinDetailLocalDataSource,
    private val dispatcher: CoroutineDispatcher
) : ICoinDetailRepository {

    override suspend fun getCoinDetailByID(coinID: String): ResponseState<CoinDetail> {
        val localDataState = localDataSource.getCoinDetailByID(coinID)
        return if (localDataState is ResponseState.Data) {
            localDataState
        } else {
            val remoteDataState = remoteDataSource.getCoinDetailByID(coinID)
            if (remoteDataState is ResponseState.Data) {
                CoroutineScope(dispatcher).launch {
                    localDataSource.saveCoinDetails(remoteDataState.data!!)
                }
            }
            remoteDataState
        }
    }

}