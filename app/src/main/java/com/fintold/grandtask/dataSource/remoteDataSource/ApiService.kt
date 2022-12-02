package com.fintold.grandtask.dataSource.remoteDataSource

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

const val TOPICS_BASE_URL = "https://www.reddit.com/r/Kotlin/"

private val topicsRetrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(TOPICS_BASE_URL)
    .build()

interface ApiService {
    @GET(".json")
    suspend fun getTopics(): String
}

object SearchApi {
    val topicsRetrofitService: ApiService by lazy {
        topicsRetrofit.create(ApiService::class.java)
    }
}