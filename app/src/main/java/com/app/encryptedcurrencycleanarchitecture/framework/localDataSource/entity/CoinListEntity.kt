package com.app.encryptedcurrencycleanarchitecture.framework.localDataSource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String? = null,
    val rank: Int? = null,
    val isActive: Boolean? = null,
    val symbol: String? = null,
)
