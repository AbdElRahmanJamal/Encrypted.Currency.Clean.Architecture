package com.app.encryptedcurrencycleanarchitecture.framework.di

import com.app.data.dataSource.coinList.ICoinListLocalDataSource
import com.app.data.dataSource.coinList.ICoinListRemoteDataSource
import com.app.data.repository.coinList.CoinListRepository
import com.app.domain.EntityMapper
import com.app.domain.entities.coinList.Coin
import com.app.domain.repository.coinList.ICoinListRepository
import com.app.domain.useCase.coinList.GetCoinListUseCase
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.CoinListLocalDataSource
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinListDao
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper.CoinListToCoinEntityListMapper
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper.ConEntityListToCoinListMapper
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.CoinListRemoteDataSource
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.CoinPaprikaApi
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDto
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.entityMapper.CoinDtoListToCoinListMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object CoinListUseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetCoinListUseCase(
        @Qualifiers.CoinListRepositoryQualifier
        coinListRepository: ICoinListRepository
    ): GetCoinListUseCase {
        return GetCoinListUseCase(coinListRepository)
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinListRepositoryQualifier
    fun providesCoinListRepository(
        @Qualifiers.CoinListRemoteDataSourceQualifier
        remoteDataSource: ICoinListRemoteDataSource,
        @Qualifiers.CoinListLocalDataSourceQualifier
        localDataSource: ICoinListLocalDataSource,
        dispatcher: CoroutineDispatcher
    ): ICoinListRepository {
        return CoinListRepository(remoteDataSource, localDataSource, dispatcher)
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinListRemoteDataSourceQualifier
    fun providesCoinListRemoteDataSource(
        api: CoinPaprikaApi,
        @Qualifiers.CoinDtoListToCoinListMapperQualifier
        mapper: EntityMapper<List<CoinDto>, List<Coin>>,
        dispatcher: CoroutineDispatcher
    ): ICoinListRemoteDataSource {
        return CoinListRemoteDataSource(api = api, mapper = mapper, dispatcher = dispatcher)
    }


    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDtoListToCoinListMapperQualifier
    fun providesCoinListDtoMapper(
    ): EntityMapper<List<CoinDto>, List<Coin>> {
        return CoinDtoListToCoinListMapper()
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinListLocalDataSourceQualifier
    fun providesCoinListLocalDataSource(
        @Qualifiers.CoinListDaoQualifier
        coinListDao: CoinListDao,
        @Qualifiers.CoinListToCoinEntityListMapperQualifier
        coinListToCoinEntityListMapper: EntityMapper<List<Coin>, List<CoinEntity>>,
        @Qualifiers.CoinEntityListToCoinListMapperQualifier
        conEntityListToCoinListMapper: EntityMapper<List<CoinEntity>, List<Coin>>
    ): ICoinListLocalDataSource {
        return CoinListLocalDataSource(
            coinListToCoinEntityListMapper = coinListToCoinEntityListMapper,
            conEntityListToCoinListMapper = conEntityListToCoinListMapper,
            coinListDao = coinListDao
        )
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinListToCoinEntityListMapperQualifier
    fun providesModelToEntityConverter(
    ): EntityMapper<List<Coin>, List<CoinEntity>> {
        return CoinListToCoinEntityListMapper()
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinEntityListToCoinListMapperQualifier
    fun providesModelFromEntityConverter(
    ): EntityMapper<List<CoinEntity>, List<Coin>> {
        return ConEntityListToCoinListMapper()
    }
}