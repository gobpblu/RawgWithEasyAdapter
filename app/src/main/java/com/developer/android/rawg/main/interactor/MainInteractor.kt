package com.developer.android.rawg.main.interactor

import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.model.genres.Genre
import com.developer.android.rawg.main.repository.MainLocalRepository
import com.developer.android.rawg.main.repository.MainRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val remoteRepository: MainRemoteRepository,
    private val localRepository: MainLocalRepository
) {
    suspend fun getGames(page: Int, genre: String): List<FullGame> {
        return withContext(Dispatchers.IO) {
            val localData = localRepository.getGames(page, genre)
            Timber.d("")
            localData.ifEmpty {
                val remoteData = remoteRepository.getGames(page, genre)
                localRepository.setGames(remoteData)
                remoteData
            }
        }
    }

    suspend fun getGenres(): List<Genre> {
        return withContext(Dispatchers.IO) {
            val localData = localRepository.getGenres()
            localData.ifEmpty {
                val remoteData = remoteRepository.getGenres()
                localRepository.setGenres(remoteData)
                remoteData
            }
        }
    }
}