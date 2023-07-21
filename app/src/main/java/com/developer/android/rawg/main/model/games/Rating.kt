package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Float
) : Parcelable
