package com.arcanesecurity.pixabayapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arcanesecurity.pixabayapp.R
import com.arcanesecurity.pixabayapp.databinding.FeedVideoItemBinding
import com.arcanesecurity.pixabayapp.model.VideoConfig
import com.bumptech.glide.Glide

const val NORMAL_VIEW = 0
const val ADS_VIEW = 1

class FeedVideoAdapter : ListAdapter<VideoConfig, RecyclerView.ViewHolder>(VideosDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ADS_VIEW) {
            LayoutInflater.from(parent.context).inflate(R.layout.ads_item, parent, false).apply {
                return AdsViewHolder(this)
            }
        }
        LayoutInflater.from(parent.context).inflate(R.layout.feed_video_item, parent, false).apply {
            return FeedVideoViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == NORMAL_VIEW) {
            holder as FeedVideoViewHolder
            getItem(position).let { video ->
                holder.bind(video)
            }
        } else {
            holder as AdsViewHolder
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 3) ADS_VIEW else NORMAL_VIEW
    }
}

class FeedVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: FeedVideoItemBinding = FeedVideoItemBinding.bind(itemView)

    fun bind(video: VideoConfig) {
        binding.textViewName.text = video.user
        binding.textViewLikes.text = video.likes.toString()
        Glide.with(itemView).load(video.userImageURL).into(binding.imageViewAvatar)
        binding.videoView.setVideoURI(Uri.parse(video.videos.medium.url))
        binding.videoView.start()
        binding.bottomBarInfo.visibility = View.GONE
        binding.videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
            val screenRatio = binding.videoView.width / binding.videoView.height.toFloat()
            val scaleX = videoRatio / screenRatio
            if (scaleX >= 1f) {
                binding.videoView.scaleX = scaleX
            } else {
                binding.videoView.scaleY = 1f / scaleX
            }
            binding.bottomBarInfo.visibility = View.VISIBLE
        }

    }
}