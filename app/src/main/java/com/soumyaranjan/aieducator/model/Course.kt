package com.soumyaranjan.aieducator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Course(
    val id : String,
    val title : String,
    val description : String,
    val no_of_videos : String,
    val level : String,
    val instructor : String,
    val topics : List<Video>
) : Parcelable