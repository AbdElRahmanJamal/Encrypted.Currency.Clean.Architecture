package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource

import com.app.data.dataSource.coinList.ICoinListLocalDataSource
import com.app.domain.EntityMapper
import com.app.domain.entities.coinList.Coin
import com.app.domain.utils.Constant
import com.app.domain.utils.ResponseState
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinListDao
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinEntity
import javax.inject.Inject

class CoinListLocalDataSource @Inject constructor(
        private val coinListDao: CoinListDao,
        private val coinListToCoinEntityListMapper: EntityMapper<List<Coin>, List<CoinEntity>>,
        private val conEntityListToCoinListMapper: EntityMapper<List<CoinEntity>, List<Coin>>
) : ICoinListLocalDataSource {

    override suspend fun insertListOfCoin(coinList: List<Coin>) {
        coinListDao.insertCoinList(coinListToCoinEntityListMapper.convert(coinList))
    }

    override suspend fun getListOfCoin(): ResponseState<List<Coin>> {
        return try {
            val coinList = coinListDao.getListOfCoins()
            if (coinList.isNotEmpty()) {
                ResponseState.Data(conEntityListToCoinListMapper.convert(coinList))
            } else {
                ResponseState.Error(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
            }
        } catch (ex: Exception) {
            ResponseState.Error(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
        }
    }
}