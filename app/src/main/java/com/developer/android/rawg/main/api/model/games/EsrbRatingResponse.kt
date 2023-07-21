package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class EsrbRatingResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
)
