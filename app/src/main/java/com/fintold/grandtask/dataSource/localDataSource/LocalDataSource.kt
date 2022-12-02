package com.fintold.grandtask.dataSource.localDataSource

import com.fintold.grandtask.adapters.Result
import com.fintold.grandtask.dataSource.DataSource
import com.fintold.grandtask.dataSource.RedditTopic

class LocalDataSource(private val database: DatabaseDao): DataSource {
    override suspend fun getTopics(): Result<List<RedditTopic>> {
        return try {
            val result = database.getTopics()
            Result.Success(result)
        } catch (ex: Exception) {
            Result.Error("Can not fetch data from Api ${ex.message}")
        }
    }

    override suspend fun addTopics(topics: List<RedditTopic>): Boolean {
        return try {
            database.addTopics(*topics.toTypedArray())
            true
        } catch(ex: Exception) {
            false
        }
    }

    override suspend fun deleteTopics() {
        database.deleteTopics()
    }
}