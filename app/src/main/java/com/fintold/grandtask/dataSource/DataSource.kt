package com.fintold.grandtask.dataSource

import com.fintold.grandtask.adapters.Result

interface DataSource {
    suspend fun getTopics(): Result<List<RedditTopic>>
    suspend fun addTopics(topics: List<RedditTopic>): Boolean
    suspend fun deleteTopics()
}