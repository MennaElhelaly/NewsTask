package com.menna.newstask.data_layer.entity

import com.menna.newstask.data_layer.entity.Article

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)