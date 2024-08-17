package com.soumyaranjan.aieducator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val title: String,
    val viewCount: String,
    val publishedDate: String,
    val author: String,
    val length: String,
    val videoId: String,
    val videoUrl: String?,
    val thumbnailUrl: String?
) : Parcelable