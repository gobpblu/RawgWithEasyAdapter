package com.developer.android.rawg.main.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.main.api.model.games.GameDetailsResponse
import com.developer.android.rawg.utils.Utils.API_KEY
import timber.log.Timber
import java.lang.Exception

class MainPagingSource(
    private val rawgApi: RawgApi,
    private val genre: String,
) : PagingSource<Int, GameDetailsResponse>() {

    override fun getRefreshKey(state: PagingState<Int, GameDetailsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDetailsResponse> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = rawgApi.getGames(
                key = API_KEY,
                genres = genre,
                page = nextPageNumber
            )
            return LoadResult.Page(
                data = response.games,
                prevKey = if (response.previous != null) nextPageNumber - 1 else null,
                nextKey = if (response.next != null) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            Timber.e(e)
            return LoadResult.Error(e)
        }
    }

}