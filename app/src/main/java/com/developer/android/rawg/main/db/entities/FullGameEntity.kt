package com.developer.android.rawg.main.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.model.games.ParentPlatformContainer
import com.developer.android.rawg.main.model.games.ShortScreenshot
import com.developer.android.rawg.main.model.genres.Genre

@Entity(tableName = "games")
data class FullGameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
) {
    fun toFullGame() = FullGame(
        id = id,
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
