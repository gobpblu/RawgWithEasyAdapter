package com.developer.android.rawg.main.model.genres

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(
    val count: Int,
    val gamesGenres: List<GameGenre>
) : Parcelable
