package com.menna.newstask.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.menna.newstask.data_layer.entity.Article
import com.menna.newstask.data_layer.remote_source.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var newsArticlesLiveData = MutableLiveData<List<Article>>()
    var apiRepository: RemoteDataSource = RemoteDataSource()

    fun getAPINews() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiRepository.getAPINews()
            newsArticlesLiveData.postValue(response?.articles)
        }

    }
}