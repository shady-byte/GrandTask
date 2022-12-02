package com.fintold.grandtask.repositories

import android.util.Log
import com.fintold.grandtask.adapters.Result
import com.fintold.grandtask.dataSource.DataSource
import com.fintold.grandtask.dataSource.RedditTopic
import com.fintold.grandtask.dataSource.localDataSource.LocalDataSource
import com.fintold.grandtask.dataSource.remoteDataSource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopicsRepository(private val localSource: DataSource, private val remoteSource: DataSource) {

    suspend fun getTopics(): Result<List<RedditTopic>> {
        return withContext(Dispatchers.IO) {
            try {
                refreshTopics()
                localSource.getTopics()
            } catch (ex:Exception) {
                Result.Error("Error fetching topics from database")
            }
        }
    }

    private suspend fun refreshTopics() {
        withContext(Dispatchers.IO) {
            try {
                val result = remoteSource.getTopics()
                if(result is Result.Success) {
                    localSource.deleteTopics()
                    localSource.addTopics(result.data)
                } else {
                    Log.d("RepositoryTest","can not refresh local data from remote datasource")
                }
            } catch (ex: Exception) {
                Log.d("RepositoryTest","can not refresh local data from remote datasource")
            }
        }
    }

}