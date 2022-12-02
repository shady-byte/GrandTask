package com.fintold.grandtask.dataSource.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fintold.grandtask.dataSource.RedditTopic

@Dao
interface DatabaseDao {
//add topics to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopics(vararg topics: RedditTopic)

//add topics to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopic(topic: RedditTopic)

// get topics from database
    @Query("SELECT * FROM reddit_topics_table")
    suspend fun getTopics():List<RedditTopic>

// get topics from database
    @Query("DELETE FROM reddit_topics_table")
    suspend fun deleteTopics()

}