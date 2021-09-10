package com.arcanesecurity.pixabayapp.model

import com.google.gson.annotations.SerializedName

data class PixabayImage(
    @SerializedName("total")
    val total : Int,
    @SerializedName("hits")
    val hits : List<Image>
)

data class Image(
    val id: Int,
    val tags: String,
    val likes: Int,
    val previewURL: String,
    val largeImageURL: String,
    val fullHDURL: String,
    val imageURL: String,
    val user: String,
    val userImageURL: String,
)
