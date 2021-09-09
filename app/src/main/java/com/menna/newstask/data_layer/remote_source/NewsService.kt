package com.menna.newstask.data_layer.remote_source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsService {
    private const val BASE_URL = "https://newsapi.org/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val newsService: NewsAPI = getRetrofit()
        .create(NewsAPI::class.java)
}