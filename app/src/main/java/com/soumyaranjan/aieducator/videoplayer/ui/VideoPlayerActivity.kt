package com.soumyaranjan.aieducator.videoplayer.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.databinding.ActivityVideoPlayerBinding
import com.soumyaranjan.aieducator.model.Course
import com.soumyaranjan.aieducator.model.Video
import com.soumyaranjan.aieducator.videoplayer.adapter.VideoPlayerQueueAdapter

class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = DataBindingUtil.setContentView<ActivityVideoPlayerBinding>(this, R.layout.activity_video_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intentDataList = intent.extras?.getParcelableArray("List")?.filterIsInstance<Video>()
        val intentDataVideo = intent.extras?.getParcelable<Video>("Video")

        binding.videoPlayerList.adapter = VideoPlayerQueueAdapter(intentDataList!!, this, intentDataVideo!!, this)
        binding.videoTitle.text = intentDataVideo.title
        binding.videoInstructor.text = intentDataVideo.author
        binding.videoPlayerList.scrollToPosition(intentDataList.indexOf(intentDataVideo))
    
        val videoId = extractYouTubeVideoId(intentDataVideo!!.videoUrl)
        val videoHtml = """
            <html>
                <body style="margin:0;padding:0;">
                    <iframe width="100%" height="100%" 
                            src="https://www.youtube.com/embed/$videoId" 
                            frameborder="0" 
                            allowfullscreen>
                    </iframe>
                </body>
            </html>
            """

        binding.videoPlayer.settings.javaScriptEnabled = true
        binding.videoPlayer.loadData(videoHtml, "text/html", "utf-8")
    }

    fun extractYouTubeVideoId(url: String): String? {
        val indexOfId = url.indexOf("v=")
        return if (indexOfId != -1) {
            url.substring(indexOfId + 2, indexOfId + 13)
        } else {
            null
        }
    }
}