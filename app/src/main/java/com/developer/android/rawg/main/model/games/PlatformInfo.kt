package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlatformInfo(
    val id: Int,
    val name: String,
    val slug: String,
    val image: String?,
    val yearEnd: Int?,
    val yearStart: Int?,
    val gamesCount: Int,
    val imageBackground: String,
) : Parcelable
