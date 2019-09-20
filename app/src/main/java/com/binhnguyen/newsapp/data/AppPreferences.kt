package com.binhnguyen.newsapp.data

import android.content.Context
import com.binhnguyen.newsapp.network.ApiFilter

class AppPreferences (context: Context) : Preferences(context, "AppPreferences") {
    var filterKey by stringPref()
    var getFilterKey = {
        filterKey?:ApiFilter.BITCOIN.value
    }
}