package com.developer.android.rawg.main.ui.main

import com.developer.android.rawg.main.model.games.FullGame
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState

sealed class MainUi {

    data class Genre(val name: String) : MainUi()

    data class GamesList(
        val genre: String,
        val games: DataList<FullGame>,
        val paginationState: PaginationState,
        val page: Int,
        val lastVisiblePosition: Int
    ) : MainUi()

}
