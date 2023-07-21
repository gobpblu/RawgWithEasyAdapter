package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.model.games.Games
import com.developer.android.rawg.main.model.genres.Genres

interface MainRepository {
    suspend fun getGames(page: Int = 1, genres: String): Games

    suspend fun getGenres(): Genres
}