package com.binhnguyen.newsapp.network

data class NewsSources (
    val status: String,
    val totalResults: Int,
    val articles: List<News> = emptyList()
)