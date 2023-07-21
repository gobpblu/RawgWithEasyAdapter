package com.developer.android.rawg.main.ui.main

import com.developer.android.rawg.main.model.GameType
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState

sealed class MainUi {

    data class Genre(val name: String) : MainUi()

    data class GamesList(
        val genre: String,
        val games: DataList<GameType>,
        val paginationState: PaginationState,
        val page: Int,
        val lastVisiblePosition: Int
    ) : MainUi()

}
