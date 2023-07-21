package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class Year(
    @SerializedName("from")
    val from: Int,
    @SerializedName("to")
    val to: Int,
    @SerializedName("filter")
    val filter: String,
    @SerializedName("decade")
    val decade: Int,
    @SerializedName("years")
    val yearsDetails: List<YearDetails>,
    @SerializedName("nofollow")
    val noFollow: Boolean,
    @SerializedName("count")
    val count: Int
)
