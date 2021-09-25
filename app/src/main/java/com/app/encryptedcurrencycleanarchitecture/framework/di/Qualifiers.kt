package com.app.encryptedcurrencycleanarchitecture.framework.di

import javax.inject.Qualifier

object Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinListRepositoryQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinListRemoteDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinListLocalDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDtoListToCoinListMapperQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailRepositoryQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailRemoteDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailLocalDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailDtoToCoinDetailMapperQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinListDaoQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinListToCoinEntityListMapperQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinEntityListToCoinListMapperQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailToCoinDetailEntityMapperQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailDaoQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailToTeamMemberEntityListMapperQualifer

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailEntityToCoinDetailMapperQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinDetailToTagEntityListMapperQualifier
}