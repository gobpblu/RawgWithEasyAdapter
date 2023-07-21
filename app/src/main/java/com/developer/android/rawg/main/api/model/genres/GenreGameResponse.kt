package com.developer.android.rawg.main.api.model.genres

import com.google.gson.annotations.SerializedName

data class GenreGameResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("added")
    val added: Int
)
