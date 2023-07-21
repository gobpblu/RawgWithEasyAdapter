package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.main.model.games.Games
import com.developer.android.rawg.main.model.MainConverter
import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.model.genres.Genre
import com.developer.android.rawg.utils.Utils.API_KEY
import javax.inject.Inject

class MainRemoteRemoteRepositoryImpl @Inject constructor(
    private val api: RawgApi
): MainRemoteRepository {
    override suspend fun getGames(page: Int, genres: String): List<FullGame> {
        val data = api.getGames(API_KEY, genres, page)
        return MainConverter.convertToFullGames(data, genres)
    }

    override suspend fun getGenres(): List<Genre> {
        val data = api.getAllGenres(API_KEY)
        return MainConverter.fromNetwork(data).genresList
    }


}