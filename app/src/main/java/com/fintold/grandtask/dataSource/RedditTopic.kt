package com.fintold.grandtask.dataSource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reddit_topics_table")
data class RedditTopic(

    @ColumnInfo(name = "title_column")
    val title: String,

    @ColumnInfo(name = "imageUrl_column")
    var imageUrl: String= "",

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
)
