package com.binhnguyen.newsapp.data

import android.content.Context

class UserPreferences (context: Context) : Preferences(context, "UserPreferences") {
    var username by stringPref()
}