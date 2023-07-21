package com.developer.android.rawg.main.api.model.genres

import com.google.gson.annotations.SerializedName

data class GenresResponse(
 @SerializedName("count")
 val count: Int,
 @SerializedName("next")
 val next: String?,
 @SerializedName("previous")
 val previous: String?,
 @SerializedName("results")
 val genres: List<GenreDetailsResponse>
)