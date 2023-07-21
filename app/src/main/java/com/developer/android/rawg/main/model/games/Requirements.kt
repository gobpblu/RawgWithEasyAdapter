package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Requirements(
    val minimum: String,
    val recommended: String
) : Parcelable
