package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameGenre(
    val id: Int,
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageBackground: String?,
) : Parcelable
