package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao

import androidx.room.*
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinEntity

@Dao
interface CoinListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinList(coin: List<CoinEntity>)

    @Transaction
    @Query("SELECT * from CoinEntity")
    suspend fun getListOfCoins(): List<CoinEntity>
}