package com.soumyaranjan.aieducator.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import coil.load
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.adapter.VideoRecyclerAdapter
import com.soumyaranjan.aieducator.databinding.ActivityCourseDetailsBinding
import com.soumyaranjan.aieducator.model.Course

class CourseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = DataBindingUtil.setContentView<ActivityCourseDetailsBinding>(this,R.layout.activity_course_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val course = intent.extras?.get("Course") as Course

        with(binding){
            courseDetailsImg.load(course.topics.first().thumbnailUrl){
                placeholder(R.drawable.baseline_videocam_24)
                crossfade(true)
                crossfade(500)
                error(R.drawable.baseline_videocam_off_24)
            }
            courseDetailsTitle.text = course.title
            courseInstructor.text = course.instructor
            courseDetailsDesc.text = course.description
            coursesVideosRecycler.adapter = VideoRecyclerAdapter(course.topics, this@CourseDetailsActivity)
        }

    }
}