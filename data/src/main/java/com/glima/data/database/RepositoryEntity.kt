package com.glima.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_repository")
data class RepositoryEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val forks: Int,
    val stars: Int,
    val description: String?,
    @Embedded val owner: OwnerEntity
)

@Entity
data class OwnerEntity(
    val owner: String,
    val avatar: String
)