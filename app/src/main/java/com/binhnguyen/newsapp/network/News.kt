package com.binhnguyen.newsapp.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize data class News (
    val urlToImage: String,
    val url: String,
    val title: String,
    val description: String
) : Parcelable