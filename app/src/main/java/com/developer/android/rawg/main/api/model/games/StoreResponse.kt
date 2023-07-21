package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("store")
    val storeInfo: StoreInfoResponse
)
