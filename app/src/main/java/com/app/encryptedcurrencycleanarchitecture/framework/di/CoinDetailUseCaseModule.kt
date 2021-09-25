package com.app.encryptedcurrencycleanarchitecture.framework.di

import com.app.data.dataSource.coinDetail.ICoinDetailLocalDataSource
import com.app.data.dataSource.coinDetail.ICoinDetailRemoteDataSource
import com.app.data.repository.coinDetail.CoinDetailRepository
import com.app.domain.EntityMapper
import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.repository.coinDetail.ICoinDetailRepository
import com.app.domain.useCase.coinDetail.GetCoinDetailByIDUseCase
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.CoinDetailLocalDataSource
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinDetailDao
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailWithTeamMembersWithTags
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TagEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TeamMemberEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper.CoinDetailEntityToCoinDetailMapper
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper.CoinDetailToCoinDetailEntityMapper
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper.CoinDetailToTagEntityListMapper
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper.CoinDetailToTeamMemberEntityListMapper
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.CoinDetailRemoteDataSource
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.CoinPaprikaApi
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDetailDto
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.entityMapper.CoinDetailDtoToCoinDetailMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object CoinDetailUseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetCoinDetailUseCase(
        @Qualifiers.CoinDetailRepositoryQualifier
        coinDetailRepository: ICoinDetailRepository
    ): GetCoinDetailByIDUseCase {
        return GetCoinDetailByIDUseCase(coinDetailRepository)
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailRepositoryQualifier
    fun providesCoinDetailRepository(
        @Qualifiers.CoinDetailRemoteDataSourceQualifier
        remoteDataSource: ICoinDetailRemoteDataSource,
        @Qualifiers.CoinDetailLocalDataSourceQualifier
        localDataSource: ICoinDetailLocalDataSource,
        dispatcher: CoroutineDispatcher
    ): ICoinDetailRepository {
        return CoinDetailRepository(remoteDataSource, localDataSource, dispatcher)
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailRemoteDataSourceQualifier
    fun providesCoinDetailRemoteDataSource(
        api: CoinPaprikaApi,
        @Qualifiers.CoinDetailDtoToCoinDetailMapperQualifier
        mapper: EntityMapper<CoinDetailDto, CoinDetail>,
        dispatcher: CoroutineDispatcher
    ): ICoinDetailRemoteDataSource {
        return CoinDetailRemoteDataSource(api = api, mapper = mapper, dispatcher = dispatcher)
    }


    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailDtoToCoinDetailMapperQualifier
    fun providesCoinDetailDtoCoinDetailMapper(
    ): EntityMapper<CoinDetailDto, CoinDetail> {
        return CoinDetailDtoToCoinDetailMapper()
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailLocalDataSourceQualifier
    fun providesCoinDetailLocalDataSource(
        @Qualifiers.CoinDetailDaoQualifier
        coinDetailDao: CoinDetailDao,
        @Qualifiers.CoinDetailToCoinDetailEntityMapperQualifier
        coinDetailToCoinDetailEntityMapper: EntityMapper<CoinDetail, CoinDetailEntity>,
        @Qualifiers.CoinDetailToTeamMemberEntityListMapperQualifer
        coinDetailToTeamMemberListEntityMapper: EntityMapper<CoinDetail, List<TeamMemberEntity>>,
        @Qualifiers.CoinDetailEntityToCoinDetailMapperQualifier
        coinDetailEntityToCoinDetailMapper: EntityMapper<CoinDetailWithTeamMembersWithTags, CoinDetail>,
        @Qualifiers.CoinDetailToTagEntityListMapperQualifier
        coinDetailToTagListEntityMapper: EntityMapper<CoinDetail, List<TagEntity>>

    ): ICoinDetailLocalDataSource {
        return CoinDetailLocalDataSource(
            coinDetailDao = coinDetailDao,
            coinDetailToCoinDetailEntityMapper = coinDetailToCoinDetailEntityMapper,
            coinDetailToTeamMemberListEntityMapper = coinDetailToTeamMemberListEntityMapper,
            coinDetailEntityToCoinDetailMapper = coinDetailEntityToCoinDetailMapper,
            coinDetailToTagListEntityMapper = coinDetailToTagListEntityMapper
        )
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailToCoinDetailEntityMapperQualifier
    fun providesCoinDetailToCoinDetailEntityMapper(
    ): EntityMapper<CoinDetail, CoinDetailEntity> {
        return CoinDetailToCoinDetailEntityMapper()
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailToTagEntityListMapperQualifier
    fun providesCoinDetailToTagEntityListMapper(
    ): EntityMapper<CoinDetail, List<TagEntity>> {
        return CoinDetailToTagEntityListMapper()
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailToTeamMemberEntityListMapperQualifer
    fun providesCoinDetailToTeamMemberEntityListMapper(
    ): EntityMapper<CoinDetail, List<TeamMemberEntity>> {
        return CoinDetailToTeamMemberEntityListMapper()
    }

    @Provides
    @ViewModelScoped
    @Qualifiers.CoinDetailEntityToCoinDetailMapperQualifier
    fun providesCoinDetailEntityToCoinDetailMapper(
    ): EntityMapper<CoinDetailWithTeamMembersWithTags, CoinDetail> {
        return CoinDetailEntityToCoinDetailMapper()
    }
}