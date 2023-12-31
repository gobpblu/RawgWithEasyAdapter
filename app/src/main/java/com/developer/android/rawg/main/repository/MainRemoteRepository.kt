package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.model.genres.Genre

interface MainRemoteRepository {
    suspend fun getGames(page: Int, genres: String): List<FullGame>

    suspend fun getGenres(): List<Genre>
}