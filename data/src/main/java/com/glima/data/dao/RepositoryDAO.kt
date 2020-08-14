package com.glima.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.glima.data.domain.RepositoryEntity

@Dao
interface RepositoryDAO {
    @Query("SELECT * FROM github_repository")
    fun getAll(): LiveData<List<RepositoryEntity>>

    @Insert
    fun insertAll(vararg users: RepositoryEntity)
}