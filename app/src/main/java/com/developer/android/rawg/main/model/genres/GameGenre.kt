package com.developer.android.rawg.main.model.genres

import android.os.Parcelable
import com.developer.android.rawg.common.mvp.Item
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.GameType
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameGenre(
    val name: String,
    val slug: String,
    var position: Int = -1,
    var page: Int = 1,
    var gamesList: MutableList<GameType?> = mutableListOf(),
    var latestScrollPosition: Int = 0,
    var pagingState: PagingState = PagingState.Idle,
    var state: Parcelable? = null
) : Parcelable, Item
