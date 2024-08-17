package com.soumyaranjan.aieducator

import androidx.lifecycle.ViewModel
import com.soumyaranjan.aieducator.model.Course

class MainViewModel(private val repository: Repository): ViewModel() {

    val courcesList = repository.getCourses()



}