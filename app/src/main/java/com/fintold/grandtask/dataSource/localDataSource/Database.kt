package com.fintold.grandtask.dataSource.localDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fintold.grandtask.dataSource.RedditTopic

@Database(entities = [RedditTopic::class],version = 4,exportSchema = false)
abstract class TopicsDatabase: RoomDatabase() {
    abstract val databaseDao: DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: TopicsDatabase?=null

        fun getInstance(context: Context) : TopicsDatabase {
            synchronized(lock = this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TopicsDatabase::class.java, "reddit_topics_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}