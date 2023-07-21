package com.developer.android.rawg.main.interactor

import com.developer.android.rawg.main.repository.MainRepository
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val remoteRepository: MainRepository,
) {
    suspend fun getGames(page: Int, genres: String) =
        remoteRepository.getGames(page, genres)

    suspend fun getGenres() = remoteRepository.getGenres()
}