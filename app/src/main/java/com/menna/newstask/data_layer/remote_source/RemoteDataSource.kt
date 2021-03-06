package com.menna.newstask.data_layer.remote_source

import android.util.Log
import com.menna.newstask.data_layer.entity.News

class RemoteDataSource (){
    suspend fun getAPINews(): News?{
        val response = NewsService.newsService.getAPINews(country =COUNTRY , apiKey =APIKEY)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.i("Menna", "response" + it)
                    return it
                }
            } else {
                Log.i("Menna", "response failuer" + response.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Menna", " error?" + e.printStackTrace())
        }
        return null
    }

    companion object {
        private const val COUNTRY = "eg"
        private const val APIKEY ="63b1f94dad044add871d1e319c630265"
    }

}