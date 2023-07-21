package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Platform(
    val platformInfo: PlatformInfo,
    val releasedAt: String?,
    val requirementsEn: Requirements?,
    val requirementsRu: Requirements?
) : Parcelable
