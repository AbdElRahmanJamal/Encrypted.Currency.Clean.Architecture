package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entityMapper

import com.app.domain.EntityMapper
import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.entities.coinDetail.TeamMemberInfo
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailWithTeamMembersWithTags
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TagEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TeamMemberEntity
import javax.inject.Inject

class CoinDetailToCoinDetailEntityMapper @Inject constructor() : EntityMapper<CoinDetail, CoinDetailEntity> {

    override fun convert(first: CoinDetail): CoinDetailEntity {
        return CoinDetailEntity(
                id = first.id ?: "",
                name = first.name,
                description = first.description,
                symbol = first.symbol,
                rank = first.rank,
                isActive = first.isActive
        )
    }
}

class CoinDetailToTagEntityListMapper @Inject constructor() : EntityMapper<CoinDetail, List<TagEntity>> {

    override fun convert(first: CoinDetail): List<TagEntity> {
        return first.tags?.let { list ->
            list.map {
                TagEntity(
                        name = it,
                        coinDetailEntityID = first.id ?: ""
                )
            }
        } ?: emptyList()
    }
}

class CoinDetailToTeamMemberEntityListMapper @Inject constructor() : EntityMapper<CoinDetail, List<TeamMemberEntity>> {

    override fun convert(first: CoinDetail): List<TeamMemberEntity> {
        return first.teamMemberInfo?.let { list ->
            list.map {
                TeamMemberEntity(
                        name = it.name,
                        position = it.position,
                        coinDetailEntityID = first.id ?: ""
                )
            }
        } ?: emptyList()
    }

}

class CoinDetailEntityToCoinDetailMapper @Inject constructor() : EntityMapper<CoinDetailWithTeamMembersWithTags, CoinDetail> {

    override fun convert(first: CoinDetailWithTeamMembersWithTags): CoinDetail {
        return CoinDetail(
                id = first.coinDetailEntity.id,
                name = first.coinDetailEntity.name,
                description = first.coinDetailEntity.description,
                symbol = first.coinDetailEntity.symbol,
                rank = first.coinDetailEntity.rank,
                isActive = first.coinDetailEntity.isActive,
                tags = convertFromTagsEntity(first.tags),
                teamMemberInfo = convertFromTeamMemberEntity(first.teamMemberInfo)
        )
    }

    private fun convertFromTagsEntity(tags: List<TagEntity>): List<String> {
        return tags.map { it.name }
    }

    private fun convertFromTeamMemberEntity(teamMemberInfo: List<TeamMemberEntity>): List<TeamMemberInfo> {
        return teamMemberInfo.map {
            TeamMemberInfo(
                    name = it.name,
                    position = it.position
            )
        }
    }
}