package com.menna.newstask.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.menna.newstask.data_layer.entity.Article
import com.menna.newstask.data_layer.remote_source.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var newsArticlesLiveData = MutableLiveData<List<Article>>()
    var apiRepository: RemoteDataSource

    init{
        apiRepository = RemoteDataSource(application)
    }

    fun getAPINews() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiRepository.getAPINews()
            Log.i("Menna", "" + response)
            newsArticlesLiveData.postValue(response?.articles)
        }

    }
}