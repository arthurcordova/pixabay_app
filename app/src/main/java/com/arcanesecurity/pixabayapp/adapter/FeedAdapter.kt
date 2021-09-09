package com.arcanesecurity.pixabayapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arcanesecurity.pixabayapp.R
import com.arcanesecurity.pixabayapp.databinding.FeedItemBinding
import com.arcanesecurity.pixabayapp.model.Image
import com.bumptech.glide.Glide

class FeedAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private var listOf = emptyList<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false).apply {
            return FeedViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        listOf[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOf.size

    fun update(newList: List<Image>) {
        val diffCallback = ImagesDiffCallback(oldList = listOf, newList = newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listOf = newList
        diffResult.dispatchUpdatesTo(this)

    }
}

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding : FeedItemBinding = FeedItemBinding.bind(itemView)

    fun bind(image: Image) {
        binding.textViewName.text = image.user
        Glide.with(itemView).load(image.largeImageURL).into(binding.imageViewPhoto)
        Glide.with(itemView).load(image.userImageURL).into(binding.imageViewAvatar)
    }


}