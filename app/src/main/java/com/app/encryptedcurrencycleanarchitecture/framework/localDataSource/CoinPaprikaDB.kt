package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinDetailDao
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinListDao
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TagEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TeamMemberEntity


@Database(
        entities = [CoinEntity::class, CoinDetailEntity::class,
            TeamMemberEntity::class, TagEntity::class],
        version = 1
)
abstract class CoinPaprikaDB : RoomDatabase() {

    abstract val coinListDoa: CoinListDao

    abstract val coinDetailDoa: CoinDetailDao
}