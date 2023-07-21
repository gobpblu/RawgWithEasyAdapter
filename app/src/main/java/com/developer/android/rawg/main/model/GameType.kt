package com.developer.android.rawg.main.model

import android.os.Parcelable
import com.developer.android.rawg.main.model.games.ParentPlatformContainer
import com.developer.android.rawg.main.model.games.ShortScreenshot
import kotlinx.parcelize.Parcelize

sealed class GameType : Parcelable {
    @Parcelize
    data class FullGame(
        val id: Int,
        val name: String,
        val released: String?,
        val backgroundImage: String?,
        val rating: Float,
        val metaCritic: Int,
        val playTime: Int,
        val updated: String,
        val shortScreenshots: List<ShortScreenshot>,
        val parentPlatforms: List<ParentPlatformContainer>,
    ) : GameType()

    @Parcelize
    data class ImageGame(
        val backgroundImage: String?,
    ) : GameType()

    @Parcelize
    data class DescriptionGame(
        val name: String,
        val released: String,
        val playTime: Int,
        val parentPlatforms: List<ParentPlatformContainer>,
    ) : GameType()
}
