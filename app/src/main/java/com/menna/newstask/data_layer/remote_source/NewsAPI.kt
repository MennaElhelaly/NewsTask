package com.menna.newstask.data_layer.remote_source

import com.menna.newstask.data_layer.entity.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface NewsAPI {
    @GET("v2/top-headlines")
    suspend fun getAPINews(@Query("country") country: String,
                           @Query("apiKey") apiKey: String ): Response<News?>
}