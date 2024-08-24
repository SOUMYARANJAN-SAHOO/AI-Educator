package com.soumyaranjan.aieducator.videoplayer.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.databinding.VideoPlayerItemLayoutBinding
import com.soumyaranjan.aieducator.model.Video
import com.soumyaranjan.aieducator.videoplayer.ui.VideoPlayerActivity

class VideoPlayerQueueAdapter(private val videosList: List<Video>, private val context : Context, private val currentPlaying: Video, private val activity: VideoPlayerActivity): RecyclerView.Adapter<VideoPlayerQueueAdapter.VideoPlayerQueueRecyclerViewHolder>() {

    class VideoPlayerQueueRecyclerViewHolder(val binding: VideoPlayerItemLayoutBinding, val context: Context, val activity: VideoPlayerActivity): RecyclerView.ViewHolder(binding.root){
        fun bind(videosList: List<Video>, position: Int, currentPlaying: Video){
            with(binding){
                val item = videosList[position]
                if(item.videoId == currentPlaying.videoId){
                    videoContainer.backgroundTintList = binding.root.context.getColorStateList(R.color.primary)
                    videoName.setTextColor(binding.root.context.getColor(R.color.white))
                    videoViewCount.setTextColor(binding.root.context.getColor(R.color.white))
                    videoDate.setTextColor(binding.root.context.getColor(R.color.white))
                    videoDuration.setTextColor(binding.root.context.getColor(R.color.white))
                }else{
                    videoContainer.backgroundTintList = binding.root.context.getColorStateList(R.color.white)
                    videoName.setTextColor(binding.root.context.getColor(R.color.black))
                    videoViewCount.setTextColor(binding.root.context.getColor(R.color.black))
                    videoDate.setTextColor(binding.root.context.getColor(R.color.black))
                    videoDuration.setTextColor(binding.root.context.getColor(R.color.black))
                }
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
                    context.startActivity(Intent(context, VideoPlayerActivity::class.java).apply {
                        putExtra("Video", item)
                        putExtra("List", videosList.toTypedArray())
                    })
                    activity.finish()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPlayerQueueRecyclerViewHolder {
        val binding = VideoPlayerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoPlayerQueueRecyclerViewHolder(binding, context, activity)
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    override fun onBindViewHolder(holder: VideoPlayerQueueRecyclerViewHolder, position: Int) {
        holder.bind(videosList, position, currentPlaying)
    }
}