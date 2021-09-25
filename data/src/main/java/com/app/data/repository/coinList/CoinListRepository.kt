package com.app.data.repository.coinList

import com.app.data.dataSource.coinList.ICoinListLocalDataSource
import com.app.data.dataSource.coinList.ICoinListRemoteDataSource
import com.app.domain.entities.coinList.Coin
import com.app.domain.repository.coinList.ICoinListRepository
import com.app.domain.utils.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinListRepository @Inject constructor(
    private val remoteDataSource: ICoinListRemoteDataSource,
    private val localDataSource: ICoinListLocalDataSource,
    private val dispatcher: CoroutineDispatcher
) : ICoinListRepository {

    override suspend fun getCoinList(): ResponseState<List<Coin>> {
        val localDataState = localDataSource.getListOfCoin()
        return if (localDataState is ResponseState.Data) {
            localDataState
        } else {
            val remoteDataState = remoteDataSource.getCoinList()
            if (remoteDataState is ResponseState.Data) {
                CoroutineScope(dispatcher).launch {
                    localDataSource.insertListOfCoin(remoteDataState.data!!)
                }
            }
            remoteDataState
        }
    }
}