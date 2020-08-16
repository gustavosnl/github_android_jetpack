package com.glima.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RepositoryEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class GithubRepositoryDatabase : RoomDatabase() {

    abstract val repositoryDAO: RepositoryDAO
    abstract val remoteKeysDAO: RemoteKeysDAO

    suspend fun clear() {
        remoteKeysDAO.clearRemoteKeys()
        repositoryDAO.clearRepositories()
    }
}