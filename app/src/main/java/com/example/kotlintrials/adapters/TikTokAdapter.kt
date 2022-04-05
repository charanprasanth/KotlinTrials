package com.example.kotlintrials.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintrials.R


class TikTokAdapter(var videos: List<String>) : RecyclerView.Adapter<TikTokAdapter.VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tiktok_ui, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.setVideoData(videos[position])
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoView: VideoView = itemView.findViewById(R.id.videoView)

        fun setVideoData(videoURL: String) {
            videoView.setVideoPath(videoURL)
            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.start()
                val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
                val screenRatio = videoView.width / videoView.height
                    .toFloat()
                val scale = videoRatio / screenRatio
                if (scale >= 1f) {
                    videoView.scaleX = scale
                } else {
                    videoView.scaleY = 1f / scale
                }
            }
            videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }
        }
    }
}