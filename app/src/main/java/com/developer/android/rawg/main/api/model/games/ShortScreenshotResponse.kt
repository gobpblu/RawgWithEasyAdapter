package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class ShortScreenshotResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)
