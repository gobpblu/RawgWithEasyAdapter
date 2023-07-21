package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("years")
    val years: List<Year>
)
