package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class CoinDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val name: String? = null,
    val description: String? = null,
    val symbol: String? = null,
    val rank: Int? = null,
    val isActive: Boolean? = null,
)

@Entity
data class TeamMemberEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val position: String,
    val coinDetailEntityID: String
)

@Entity
data class TagEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val coinDetailEntityID: String
)

data class CoinDetailWithTeamMembersWithTags(
    @Embedded val coinDetailEntity: CoinDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "coinDetailEntityID"
    )
    val teamMemberInfo: List<TeamMemberEntity> = emptyList(),
    @Relation(
        parentColumn = "id",
        entityColumn = "coinDetailEntityID"
    )
    val tags: List<TagEntity> = emptyList(),
)