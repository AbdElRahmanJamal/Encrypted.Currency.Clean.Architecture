package com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.entityMapper

import com.app.domain.EntityMapper
import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.entities.coinDetail.TeamMemberInfo
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.CoinDetailDto
import com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource.dto.TeamMember
import javax.inject.Inject

class CoinDetailDtoToCoinDetailMapper @Inject constructor() : EntityMapper<CoinDetailDto, CoinDetail> {

    override fun convert(first: CoinDetailDto) = CoinDetail(
            id = first.id,
            name = first.name,
            description = first.description,
            symbol = first.symbol,
            rank = first.rank,
            isActive = first.isActive,
            tags = first.tags.map { it.name },
            teamMemberInfo = mapToTeamMemberDomainModel(first.team),
    )

    private fun mapToTeamMemberDomainModel(team: List<TeamMember>) = team.map { teamMember ->
        TeamMemberInfo(teamMember.name, teamMember.position)
    }

}