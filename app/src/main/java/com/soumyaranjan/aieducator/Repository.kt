package com.soumyaranjan.aieducator

import android.content.Context
import com.google.gson.Gson
import com.soumyaranjan.aieducator.model.Course

class Repository(private val context: Context) {

    fun getCourses(): List<Course> {
        val json = context.assets.open("data.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(json, Array<Course>::class.java).toList()
    }

}