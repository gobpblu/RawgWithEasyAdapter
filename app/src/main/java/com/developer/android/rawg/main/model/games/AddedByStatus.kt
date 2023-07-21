package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddedByStatus(
    val yet: Int,
    val owned: Int,
    val beaten: Int,
    val toplay: Int,
    val dropped: Int,
    val playing: Int,
) : Parcelable
