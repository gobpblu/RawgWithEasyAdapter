package com.developer.android.rawg.main.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.model.genres.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainInteractor: MainInteractor
) : ViewModel() {

    private val mutex = Mutex()

    init {
        loadGenres()
    }

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _mainUi = MutableStateFlow<List<MainUi>>(emptyList())
    val mainUi = _mainUi.asStateFlow()

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres = _genres.asStateFlow()

    private fun loadGenres() {
        viewModelScope.launch {
            val data = mainInteractor.getGenres()
            _genres.value = data
            _mainUi.value = addGenres(data)
        }
    }

    fun loadGames(genre: String, page: Int, position: Int) {
        viewModelScope.launch {
            try {

            val data = mainInteractor.getGames(page, genre)

            _mainUi.value = _mainUi.value.addNewGamesByGenre(data, genre, page, position)

            } catch (e: Exception) {
                Timber.e(e)
            }

        }
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val GENRE_RPG = "role-playing-games-rpg"
    }

    private fun List<MainUi>.addNewGamesByGenre(
        games: List<FullGame>,
        genre: String,
        page: Int,
        position: Int
    ): List<MainUi> =
        map { mainUi ->
            return@map when (mainUi) {
                is MainUi.GamesList -> {
                    if (mainUi.genre != genre) mainUi
                    else mainUi.copy(
                        games = mainUi.games.merge(
                            DataList(games, page, PAGE_SIZE),
                        ),
                        page = page + 1,
                        lastVisiblePosition = position // page * PAGE_SIZE - PAGE_SIZE
                    )
                }

                is MainUi.Genre -> mainUi
            }
        }

    private fun addGenres(genres: List<Genre>): List<MainUi> {
        val mainUiList: MutableList<MainUi> = mutableListOf()
        genres.forEach {
            mainUiList.add(MainUi.Genre(it.name))

            mainUiList.add(
                MainUi.GamesList(
                    genre = it.slug,
                    games = DataList(emptyList(), 1, PAGE_SIZE),
                    paginationState = PaginationState.READY,
                    page = 1,
                    lastVisiblePosition = 0
                )
            )
        }
        return mainUiList
    }

}