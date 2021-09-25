package com.app.encryptedcurrencycleanarchitecture.framework.di

import android.content.Context
import androidx.room.Room
import com.app.domain.utils.Constant
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.CoinPaprikaDB
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinDetailDao
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinPaprikaDBModule {

    @Provides
    @Singleton
    @Qualifiers.CoinListDaoQualifier
    fun providesCoinListDao(coinPaprikaDB: CoinPaprikaDB): CoinListDao =
        coinPaprikaDB.coinListDoa

    @Provides
    @Singleton
    @Qualifiers.CoinDetailDaoQualifier
    fun providesCoinDetailDao(coinPaprikaDB: CoinPaprikaDB): CoinDetailDao =
        coinPaprikaDB.coinDetailDoa

    @Provides
    @Singleton
    fun providesCoinPaprikaDatabase(@ApplicationContext context: Context): CoinPaprikaDB {
        synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                CoinPaprikaDB::class.java,
                Constant.DATA_BASE_NAME
            ).build().also {
                return it
            }
        }
    }

}



