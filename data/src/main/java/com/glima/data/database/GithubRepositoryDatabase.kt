package com.glima.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glima.data.dao.RepositoryDAO
import com.glima.data.domain.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class GithubRepositoryDatabase : RoomDatabase() {

    abstract val repositoryDAO: RepositoryDAO
}