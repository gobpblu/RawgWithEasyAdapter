package com.developer.android.rawg.main.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.databinding.GenreItemBinding
import com.developer.android.rawg.main.api.model.genres.GenreDetailsResponse
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.genres.Genre
import timber.log.Timber

class GenreViewHolder(
    private val binding: GenreItemBinding,
    onGameItemClicked: (GameType.FullGame) -> Unit,
    onFailedListener: () -> Unit,
    private val getGamesByGenre: (Genre) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        onGameItemClicked: (GameType.FullGame) -> Unit,
        onFailedListener: () -> Unit,
        getGamesByGenre: (Genre) -> Unit,
    ) : this(
        GenreItemBinding.inflate(LayoutInflater.from(
            parent.context), parent, false),
        onGameItemClicked, onFailedListener, getGamesByGenre)

    private val nestedAdapter = NestedAdapter(onGameItemClicked, onFailedListener)

    private val linearLayoutManager: LinearLayoutManager

    init {
        with(binding.rvGenre) {
            linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = nestedAdapter
            layoutManager = linearLayoutManager
        }
        Timber.tag("%%%").i("item")
    }

    fun bind(item: GenreDetailsResponse) {
        with(binding) {

            Timber.tag("%%%").i("$item")
//        genreRecyclerView.addOnScrollListener(MainScrollListener(
//            linearLayoutManager,
//            loadNextPage = {getGamesByGenre(it)},
//            gameGenre = item
//        ))

            textViewGenreTitle.text = item.name
//        if (item.position == -1) {
//            item.position = layoutPosition
//            getGamesByGenre(item)
//        }
//        genreRecyclerView.restoreState(item.state)
//        linearLayoutManager.scrollToPosition(item.latestScrollPosition)
//        nestedAdapter.setItems(item.gamesList as List<GameTypes.FullGame>)
        }

//    override fun onViewDetached() {
//        item.state = binding.genreRecyclerView.layoutManager?.onSaveInstanceState()
//    }
//
//    private fun RecyclerView.restoreState(parcelable: Parcelable?) {
//        if (parcelable == null) return
//        layoutManager?.onRestoreInstanceState(parcelable)
//    }
    }
}