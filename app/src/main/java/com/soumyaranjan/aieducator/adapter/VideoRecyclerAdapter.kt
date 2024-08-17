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
import com.soumyaranjan.aieducator.databinding.VideoItemBinding
import com.soumyaranjan.aieducator.model.Course
import com.soumyaranjan.aieducator.model.Video

class VideoRecyclerAdapter(private val videosList: List<Video>, private val context : Context): RecyclerView.Adapter<VideoRecyclerAdapter.VideoRecyclerViewHolder>() {

    class VideoRecyclerViewHolder(val binding: VideoItemBinding, val context: Context): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Video){
            with(binding){
                videoThumbnail.load(item.thumbnailUrl){
                    placeholder(R.drawable.baseline_videocam_24)
                    crossfade(true)
                    crossfade(500)
                    error(R.drawable.baseline_videocam_off_24)
                }
                videoName.text = item.title
                videoDate.text = item.publishedDate
                videoDuration.text = item.length
                videoViewCount.text = item.viewCount
                videoContainer.setOnClickListener {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoRecyclerViewHolder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoRecyclerViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    override fun onBindViewHolder(holder: VideoRecyclerViewHolder, position: Int) {
        holder.bind(videosList[position])
    }
}