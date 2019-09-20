package com.binhnguyen.newsapp.ui.custom

import android.app.Application
import com.binhnguyen.newsapp.data.AppPreferences
import com.binhnguyen.newsapp.network.ApiFilter
import com.binhnguyen.newsapp.network.NewsApi
import com.binhnguyen.newsapp.network.NewsSources
import com.binhnguyen.newsapp.ui.news.NewsViewModel
import kotlinx.coroutines.Deferred

class CustomViewModel(app:Application) : NewsViewModel(app) {
    private val appPreference = AppPreferences(app)
    override fun getNewsDeferred(): Deferred<NewsSources> {
        return NewsApi.retrofitService.getCustomNews(appPreference.getFilterKey())
    }

    fun updateFilter(apiFilter: ApiFilter) {
        appPreference.filterKey = apiFilter.value
        getNews()
    }
}