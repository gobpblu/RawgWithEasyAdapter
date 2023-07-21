package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    val id: Int,
    val name: String,
    val slug: String,
    val language: String,
    val gamesCount: Int,
    val imageBackground: String?
) : Parcelable
