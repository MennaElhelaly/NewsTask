package com.menna.newstask.ui.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.menna.newstask.data_layer.entity.Article
import com.menna.newstask.data_layer.remote_source.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel (application: Application) : AndroidViewModel(application) {

    var newsArticlesLiveData = MutableLiveData<List<Article>>()
    var apiRepository: RemoteDataSource = RemoteDataSource()

    fun getAPINews() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiRepository.getAPINews()
            newsArticlesLiveData.postValue(response?.articles)
        }

    }
}