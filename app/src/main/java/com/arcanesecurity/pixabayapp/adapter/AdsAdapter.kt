package com.arcanesecurity.pixabayapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arcanesecurity.pixabayapp.R
import com.arcanesecurity.pixabayapp.databinding.AdsItemBinding
import com.arcanesecurity.pixabayapp.databinding.FeedItemBinding
import com.arcanesecurity.pixabayapp.databinding.HeaderItemBinding
import com.arcanesecurity.pixabayapp.model.Image
import com.bumptech.glide.Glide

class AdsAdapter : RecyclerView.Adapter<AdsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.ads_item, parent, false).apply {
            return AdsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 1

}

class AdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: AdsItemBinding = AdsItemBinding.bind(itemView)


}