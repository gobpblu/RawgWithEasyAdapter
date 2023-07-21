package com.developer.android.rawg.main.api.model.games

import com.google.gson.annotations.SerializedName

data class PlatformResponse(
    @SerializedName("platform")
    val platformInfo: PlatformInfoResponse,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements_en")
    val requirementsEn: RequirementsResponse?,
    @SerializedName("requirements_ru")
    val requirementsRu: RequirementsResponse?
)
