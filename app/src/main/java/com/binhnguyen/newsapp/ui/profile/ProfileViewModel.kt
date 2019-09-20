package com.binhnguyen.newsapp.ui.profile

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.binhnguyen.newsapp.data.UserPreferences

class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    private val preferences = UserPreferences(app)

    private var _username = MutableLiveData<String>().apply {
        value = preferences.username
    }
    val username: LiveData<String> = _username

    private var _isInvalidInput = MutableLiveData<Boolean>()
    val isInvalidInput: LiveData<Boolean>
        get() = _isInvalidInput

    fun register(input: String) {
        if (TextUtils.isEmpty(input)) {
            _isInvalidInput.value = true
            return
        }
        _username.value = input
        preferences.username = input
    }

    fun clearError() {
        _isInvalidInput.value = false
    }

    val isRegistered = Transformations.map(_username) {
         !(TextUtils.isEmpty(it))
    }
}