package com.developer.android.rawg.main.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.genres.GameGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState
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

    private val _rpgGenreGames = MutableStateFlow<List<GameType>>(emptyList())
    val rpgGenreGames = _rpgGenreGames.asStateFlow()

    private val _genres = MutableStateFlow<List<GameGenre>>(emptyList())
    val genres = _genres.asStateFlow()

    fun loadRpgGenreGames(page: Int) {
        viewModelScope.launch {
            val data = mainInteractor.getGames(page, GENRE_RPG).games
            _rpgGenreGames.value = _rpgGenreGames.value.toMutableList().apply {
                addAll(data)
            }

        }
    }

    fun loadGenres() {
        viewModelScope.launch {
            val data = mainInteractor.getGenres().gamesGenres
            _genres.value = data
            _mainUi.value = addGenres(data)
        }
    }

    fun loadGames(genre: String, page: Int, position: Int) {
        viewModelScope.launch {
            val data = mainInteractor.getGames(page, genre).games

            _mainUi.value = _mainUi.value.addNewGamesByGenre(data, genre, page, position)

        }
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val GENRE_RPG = "role-playing-games-rpg"
    }

    private fun List<MainUi>.addNewGamesByGenre(
        games: List<GameType>,
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

    private fun addGenres(genres: List<GameGenre>): List<MainUi> {
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