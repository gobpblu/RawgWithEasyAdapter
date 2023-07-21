package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class YearDetails(
    @SerializedName("year")
    val year: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("nofollow")
    val noFollow: Boolean
)
