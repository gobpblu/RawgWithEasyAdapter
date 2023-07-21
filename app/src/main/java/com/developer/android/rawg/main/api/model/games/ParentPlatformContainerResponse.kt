package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class ParentPlatformContainerResponse(
    @SerializedName("platform")
    val parentPlatform: ParentPlatformResponse
)
