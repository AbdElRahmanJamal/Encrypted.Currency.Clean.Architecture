package com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.entityMapper

import com.app.domain.EntityMapper
import com.app.domain.entities.coinList.Coin
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDto
import javax.inject.Inject

class CoinDtoListToCoinListMapper @Inject constructor() : EntityMapper<List<CoinDto>, List<Coin>> {

    override fun convert(first: List<CoinDto>): List<Coin> {
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

class CoinListToCoinDtoListMapper @Inject constructor() :
        EntityMapper<List<Coin>, List<CoinDto>> {

    override fun convert(first: List<Coin>): List<CoinDto> {
        return first.map {
            CoinDto(
                    id = it.id ?: "",
                    name = it.name ?: "",
                    rank = it.rank ?: 0,
                    isActive = it.isActive ?: false,
                    symbol = it.symbol ?: "",
                    type = "",
                    isNew = false
            )
        }
    }

}