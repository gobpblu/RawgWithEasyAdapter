package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.db.dao.GamesDao
import com.developer.android.rawg.main.db.dao.GenresDao
import com.developer.android.rawg.main.db.entities.FullGameEntity
import com.developer.android.rawg.main.db.entities.GenreEntity
import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.model.genres.Genre
import timber.log.Timber
import javax.inject.Inject

private const val PAGE_SIZE = 20

class MainLocalRepositoryImpl @Inject constructor(
    private val genresDao: GenresDao,
    private val gamesDao: GamesDao,
) : MainLocalRepository {
    override suspend fun getGames(page: Int, genre: String): List<FullGame> {
        return gamesDao.getGames(
            genre = genre,
            limit = PAGE_SIZE,
            offset = page * PAGE_SIZE - PAGE_SIZE
        ).map(FullGameEntity::toFullGame)
    }

    override suspend fun getGenres(): List<Genre> {
        return genresDao.getGenres().map(GenreEntity::toGenre)
    }

    override suspend fun setGenres(genres: List<Genre>) {
        genresDao.setGenres(genres.map(Genre::toGenreEntity))
    }

    override suspend fun setGames(games: List<FullGame>) {
        return gamesDao.setGames(games.map(FullGame::toFullGameEntity))
    }
}