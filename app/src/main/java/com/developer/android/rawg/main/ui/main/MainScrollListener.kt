package com.developer.android.rawg.main.ui.main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.main.model.genres.GameGenre

const val VISIBLE_THRESHOLD = 10

class MainScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val loadNextPage: (GameGenre) -> Unit,
    private val gameGenre: GameGenre
): RecyclerView.OnScrollListener() {

    private var isLoading = true

    private var totalLoadedItems = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        gameGenre.latestScrollPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItems = layoutManager.childCount
        val totalItems = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (totalItems == visibleItems) return

        if (totalItems > totalLoadedItems) {
            isLoading = false
            totalLoadedItems = totalItems
            return
        }

        val shouldLoadMore = totalItems - visibleItems <= firstVisibleItemPosition + VISIBLE_THRESHOLD
        if (!isLoading && shouldLoadMore) {
            gameGenre.page++
            loadNextPage(gameGenre)
            isLoading = true
        }
    }

    fun reset() {
        isLoading = false
        totalLoadedItems = 0
        gameGenre.page = 1
    }
}