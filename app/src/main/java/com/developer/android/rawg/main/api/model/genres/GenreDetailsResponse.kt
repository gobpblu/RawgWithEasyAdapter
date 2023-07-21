package com.developer.android.rawg.main.api.model.genres

import com.google.gson.annotations.SerializedName

data class GenreDetailsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("image_background")
    val imageBackground: String,
    @SerializedName("games")
    val games: List<GenreGameResponse>
)
