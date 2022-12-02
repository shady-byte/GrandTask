package com.fintold.grandtask.dataSource.remoteDataSource

import android.util.Log
import com.fintold.grandtask.adapters.Result
import com.fintold.grandtask.dataSource.DataSource
import com.fintold.grandtask.dataSource.RedditTopic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class RemoteDataSource(): DataSource {
    override suspend fun getTopics(): Result<List<RedditTopic>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = SearchApi.topicsRetrofitService.getTopics()
                val jsonObj = JSONObject(response)
                val result = convertJsonToRedditTopics(jsonObj)
                Result.Success(result)
            } catch (ex: Exception) {
                Log.d("MainTest","error happened")
                Result.Error("Remote data source error ${ex.message}")
            }
        }
    }

    override suspend fun addTopics(topics: List<RedditTopic>): Boolean = true

    override suspend fun deleteTopics() {}

    private fun convertJsonToRedditTopics(obj: JSONObject): List<RedditTopic> {
        val topicsList = mutableListOf<RedditTopic>()
        val regex = Regex(pattern = "jpg|png|gif|bmp|jpeg")
        val results = obj.getJSONObject("data").getJSONArray("children")
        for(element in  results.iterator<JSONObject>()) {
            val obj = element.getJSONObject("data")
            val title = obj.getString("title")
            var imgUrl = ""
            if(obj.getString("media") != "null") {
                imgUrl = obj.getJSONObject("media")
                    .getJSONObject("oembed")
                    .getString("thumbnail_url")
            } else if(obj.getString("url").contains(regex)) {
                imgUrl = obj.getString("url")
            }
            val redditTopic = RedditTopic(title,imgUrl)
            topicsList.add(redditTopic)
        }
        return topicsList
    }

    operator fun <T> JSONArray.iterator(): Iterator<T> =
        (0 until this.length()).asSequence().map { this.get(it) as T }.iterator()

}