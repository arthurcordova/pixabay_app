package com.arcanesecurity.pixabayapp.model

import com.google.gson.annotations.SerializedName

data class Pixabay(
    @SerializedName("total")
    val total : Int,
    @SerializedName("hits")
    val hits : List<Image>
)

data class Image(
    val id: Int,
    val tags: String,
    val previewURL: String,
    val largeImageURL: String,
    val fullHDURL: String,
    val imageURL: String,
    val user: String,
    val userImageURL: String,
)
