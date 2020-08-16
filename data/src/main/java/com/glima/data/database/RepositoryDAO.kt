package com.glima.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepositoryDAO {
    @Query("SELECT * FROM github_repository")
    fun getAll(): PagingSource<Int, RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<RepositoryEntity>)

    @Query("DELETE FROM github_repository")
    suspend fun clearRepositories()
}