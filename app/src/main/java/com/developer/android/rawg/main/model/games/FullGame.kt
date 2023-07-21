package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import com.developer.android.rawg.main.db.entities.FullGameEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class FullGame(
    val id: Int,
    val name: String,
    val slug: String,
    val released: String?,
    val backgroundImage: String?,
    val rating: Float,
    val metaCritic: Int,
    val playTime: Int,
    val updated: String,
    val shortScreenshots: List<ShortScreenshot>,
    val parentPlatforms: List<ParentPlatformContainer>,
) : Parcelable {

    fun toFullGameEntity() = FullGameEntity(
        name = name,
        slug = slug,
        released = released,
        backgroundImage = backgroundImage,
        rating = rating,
        metaCritic = metaCritic,
        playTime = playTime,
        updated = updated,
        shortScreenshots = shortScreenshots,
        parentPlatforms = parentPlatforms
    )

}
