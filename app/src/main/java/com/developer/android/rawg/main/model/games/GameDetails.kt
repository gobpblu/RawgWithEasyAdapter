package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetails(
    val name: String,
    val released: String,
    val backgroundImage: String?,
    val rating: Float,
    val metaCritic: Int,
    val playTime: Int,
    val updated: String,
    val shortScreenshots: List<ShortScreenshot>,
    val parentPlatforms: List<ParentPlatformContainer>
) : Parcelable
