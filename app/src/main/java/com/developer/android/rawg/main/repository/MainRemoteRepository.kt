package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.main.model.games.Games
import com.developer.android.rawg.main.model.MainConverter
import com.developer.android.rawg.main.model.genres.Genres
import com.developer.android.rawg.utils.Utils
import com.developer.android.rawg.utils.Utils.API_KEY
import javax.inject.Inject

class MainRemoteRepository @Inject constructor(
    private val api: RawgApi
): MainRepository {
    override suspend fun getGames(page: Int, genres: String): Games {
        val data = api.getGames(API_KEY, genres, page)
        return MainConverter.fromNetwork(data)
    }

    override suspend fun getGenres(): Genres {
        val data = api.getAllGenres(API_KEY)
        return MainConverter.fromNetwork(data)
    }


}