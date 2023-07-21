package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.model.genres.Genre

interface MainLocalRepository {
    suspend fun getGames(page: Int = 1, genre: String): List<FullGame>
    suspend fun getGenres(): List<Genre>
    suspend fun setGenres(genres: List<Genre>)
    suspend fun setGames(games: List<FullGame>)
}