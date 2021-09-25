package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.dao

import androidx.room.*
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.CoinDetailWithTeamMembersWithTags
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TagEntity
import com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity.TeamMemberEntity

@Dao
interface CoinDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCoinDetail(coinDetail: CoinDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCoinDetailTeamMember(teamMemberEntity: List<TeamMemberEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCoinDetailTags(tags: List<TagEntity>)

    @Transaction
    @Query("SELECT * from CoinDetailEntity where id=:coinID")
    suspend fun getCoinDetailByID(coinID: String): CoinDetailWithTeamMembersWithTags

}