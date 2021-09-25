package com.app.domain.entities.coinDetail


data class CoinDetail(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val symbol: String? = null,
    val rank: Int? = null,
    val isActive: Boolean? = null,
    val tags: List<String>? = null,
    val teamMemberInfo: List<TeamMemberInfo>? = null,
)

data class TeamMemberInfo(
    val name: String,
    val position: String
)