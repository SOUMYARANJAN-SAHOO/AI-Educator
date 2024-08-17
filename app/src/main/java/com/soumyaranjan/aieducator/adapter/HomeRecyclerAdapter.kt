package com.soumyaranjan.aieducator.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.activity.CourseDetailsActivity
import com.soumyaranjan.aieducator.databinding.CourseItemBinding
import com.soumyaranjan.aieducator.model.Course

class HomeRecyclerAdapter(private val courseList: List<Course>, private val context : Context): RecyclerView.Adapter<HomeRecyclerAdapter.HomeRecyclerViewHolder>() {

    class HomeRecyclerViewHolder(val binding: CourseItemBinding, val context:Context): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Course){
            binding.courseImg.load(item.topics.first().thumbnailUrl){
                placeholder(R.drawable.baseline_videocam_24)
                crossfade(true)
                crossfade(500)
                error(R.drawable.baseline_videocam_off_24)
            }
            binding.courseName.text = item.title
            binding.courseInstructor.text = item.instructor

            binding.courseCardContainer.setOnClickListener {
                context.startActivity(Intent(context, CourseDetailsActivity::class.java).apply {
                    putExtra("Course", item)
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        val binding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRecyclerViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.bind(courseList[position])
    }
}