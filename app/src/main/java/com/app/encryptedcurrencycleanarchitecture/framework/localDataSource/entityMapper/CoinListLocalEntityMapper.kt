package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper

import com.app.domain.EntityMapper
import com.app.domain.entities.coinList.Coin
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinEntity
import javax.inject.Inject

class CoinListToCoinEntityListMapper @Inject constructor() : EntityMapper<List<Coin>, List<CoinEntity>> {

    override fun convert(first: List<Coin>): List<CoinEntity> {
        return first.map {
            CoinEntity(
                    id = it.id ?: "",
                    name = it.name,
                    rank = it.rank,
                    isActive = it.isActive,
                    symbol = it.symbol
            )
        }
    }

}

class ConEntityListToCoinListMapper @Inject constructor() : EntityMapper<List<CoinEntity>, List<Coin>> {

    override fun convert(first: List<CoinEntity>): List<Coin> {
        return first.map {
            Coin(
                    id = it.id,
                    name = it.name,
                    rank = it.rank,
                    isActive = it.isActive,
                    symbol = it.symbol
            )
        }
    }
}