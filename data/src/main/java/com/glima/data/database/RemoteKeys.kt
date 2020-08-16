package com.glima.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val repositoryId: Long,
    val previousIndex: Int?,
    val nextIndex: Int?
)
