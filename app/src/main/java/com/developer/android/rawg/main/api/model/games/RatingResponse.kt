package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("percent")
    val percent: Float
)
