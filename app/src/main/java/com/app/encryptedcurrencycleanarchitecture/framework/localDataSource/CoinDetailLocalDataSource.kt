package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource

import com.app.data.dataSource.coinDetail.ICoinDetailLocalDataSource
import com.app.domain.EntityMapper
import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.utils.Constant
import com.app.domain.utils.ResponseState
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao.CoinDetailDao
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailWithTeamMembersWithTags
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TagEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TeamMemberEntity
import javax.inject.Inject

class CoinDetailLocalDataSource @Inject constructor(
        private val coinDetailDao: CoinDetailDao,
        private val coinDetailToCoinDetailEntityMapper: EntityMapper<CoinDetail, CoinDetailEntity>,
        private val coinDetailToTeamMemberListEntityMapper: EntityMapper<CoinDetail, List<TeamMemberEntity>>,
        private val coinDetailEntityToCoinDetailMapper: EntityMapper<CoinDetailWithTeamMembersWithTags, CoinDetail>,
        private val coinDetailToTagListEntityMapper: EntityMapper<CoinDetail, List<TagEntity>>
) : ICoinDetailLocalDataSource {

    override suspend fun saveCoinDetails(coinDetail: CoinDetail) {
        coinDetailDao.saveCoinDetail(coinDetailToCoinDetailEntityMapper.convert(coinDetail))
        coinDetailDao.saveCoinDetailTeamMember(coinDetailToTeamMemberListEntityMapper.convert(coinDetail))
        coinDetailDao.saveCoinDetailTags(coinDetailToTagListEntityMapper.convert(coinDetail))
    }


    override suspend fun getCoinDetailByID(coinID: String): ResponseState<CoinDetail> {
        return try {
            val coinDetailEntity = coinDetailDao.getCoinDetailByID(coinID)
            if (coinDetailEntity.coinDetailEntity.id.isEmpty().not()) {
                ResponseState.Data(coinDetailEntityToCoinDetailMapper.convert(coinDetailEntity))
            } else {
                ResponseState.Error(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
            }
        } catch (ex: Exception) {
            ResponseState.Error(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
        }
    }

}
