package com.developer.android.rawg.main.ui.main

import android.util.Log
import android.util.Log.i
import com.developer.android.rawg.common.mvp.BasePresenter
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.model.genres.GameGenre
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainPresenter(
    private val interactor: MainInteractor,
) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    private var coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    override fun getGenres() {

        coroutineScope.launch {
            try {
                val genres = interactor.getGenres()
                view?.showGenres(genres)
            } catch (e: CancellationException) {
                Timber.e("Cancelled loading request")
            } catch (t: Throwable) {
                Timber.e(t.message)
            }
        }
    }

    override fun getGames(gameGenre: GameGenre) {

        coroutineScope.launch {
            try {
//                view?.showPagingState(PagingState.Loading, gameGenre.position)
                val data = interactor.getGames(gameGenre.page, gameGenre.slug)
//                view?.showPagingState(PagingState.Idle, gameGenre.position)
                Timber.tag("###").i("${gameGenre.position}")
                view?.showGames(data.games, gameGenre.position)
            } catch (e: CancellationException) {
                Timber.e("Cancelled loading request")
            } catch (t: Throwable) {
                Timber.e(t.message)
            }
            finally {
//                view?.showPagingState(PagingState.Idle, gameGenre.position)
            }
        }
    }

    override fun refresh(gameGenre: GameGenre) {

        coroutineScope.launch {
            view?.showRefreshing(true)
            getGames(gameGenre)
            view?.showRefreshing(false)
        }
    }
}