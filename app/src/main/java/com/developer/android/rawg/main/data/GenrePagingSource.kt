package com.developer.android.rawg.main.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.main.api.model.games.GameDetailsResponse
import com.developer.android.rawg.main.api.model.genres.GenreDetailsResponse
import com.developer.android.rawg.utils.Utils.API_KEY
import timber.log.Timber
import java.lang.Exception

class GenrePagingSource(
    private val rawgApi: RawgApi,
) : PagingSource<Int, GenreDetailsResponse>() {

    override fun getRefreshKey(state: PagingState<Int, GenreDetailsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GenreDetailsResponse> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = rawgApi.getAllGenres(
                key = API_KEY,
            )
            LoadResult.Page(
                data = response.genres,
                prevKey = if (response.previous != null) nextPageNumber - 1 else null,
                nextKey = if (response.next != null) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }

}