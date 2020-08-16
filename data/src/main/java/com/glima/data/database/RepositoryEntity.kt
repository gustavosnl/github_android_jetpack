package com.glima.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_repository")
data class RepositoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val forks: Int,
    val stars: Int,
    val description: String?,
    @Embedded val owner: Owner
)

data class Owner(
    val name: String,
    val avatar: String
)