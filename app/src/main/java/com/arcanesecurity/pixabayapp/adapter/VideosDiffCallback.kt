package com.arcanesecurity.pixabayapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.arcanesecurity.pixabayapp.model.VideoConfig

open class VideosDiffCallback(
) : DiffUtil.ItemCallback<VideoConfig>() {
    override fun areItemsTheSame(oldItem: VideoConfig, newItem: VideoConfig): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VideoConfig, newItem: VideoConfig): Boolean {
        return oldItem.id == newItem.id
    }

}