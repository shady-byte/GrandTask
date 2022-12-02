package com.fintold.grandtask

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintold.grandtask.adapters.Result
import com.fintold.grandtask.dataSource.RedditTopic
import com.fintold.grandtask.dataSource.remoteDataSource.RemoteDataSource
import com.fintold.grandtask.repositories.TopicsRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: TopicsRepository): ViewModel() {
    val topicsList = MutableLiveData<List<RedditTopic>>()
    val selectedTopic = MutableLiveData<RedditTopic>()
    fun getTopics() {
        viewModelScope.launch {
            val result = repository.getTopics()
            if(result is Result.Success) {
                topicsList.postValue(result.data)
            } else {
                Log.d("MainTest",(result as Result.Error).message.toString())
            }
        }
    }

}