package com.developer.android.rawg.main.ui.main

import com.developer.android.rawg.common.mvp.BaseFragmentContract
import com.developer.android.rawg.common.mvp.MvpPresenter
import com.developer.android.rawg.common.mvp.MvpView
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.genres.GameGenre
import com.developer.android.rawg.main.model.genres.Genres


interface MainContract : BaseFragmentContract {

    interface View : MvpView {
        fun showGenres(genres: Genres)
        fun showGames(games: List<GameType?>, position: Int)
        fun showRefreshing(isRefreshing: Boolean)
        fun showErrorMessage(e: Throwable)
        fun showPagingState(pagingState: PagingState, position: Int)
    }

    interface Presenter : MvpPresenter<View> {
        fun getGenres()
        fun getGames(gameGenre: GameGenre)
        fun refresh(gameGenre: GameGenre)
    }

}