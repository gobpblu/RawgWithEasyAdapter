package com.developer.android.rawg.main.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.genres.GameGenre

class MainAdapter(
    private val getGamesByGenre: (GameGenre) -> Unit,
    private val onGameItemClicked: (GameType.FullGame) -> Unit,
    private val onFailedListener: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val genresData = mutableListOf<GameGenre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenreViewHolder(parent, onGameItemClicked, onFailedListener, getGamesByGenre)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
//            is GenreViewHolder -> holder.onBind(genresData[position])
        }
    }


    override fun getItemCount() = genresData.size

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        when (holder) {
//            is GenreViewHolder -> holder.onViewDetached()
        }
    }

    fun setPagingState(pagingState: PagingState, position: Int) {
        when (pagingState) {
            genresData[position].pagingState -> return
            else -> {
                genresData[position].pagingState = pagingState
//                notifyItemChanged(position)
            }
        }
    }

    fun setGenres(newList: List<GameGenre>) {
        val oldList = ArrayList(genresData)
        genresData.clear()
        genresData.addAll(newList)
        DiffUtil.calculateDiff(GenresCallback(oldList, newList)).dispatchUpdatesTo(this)
    }

    fun setGames(games: List<GameType?>, position: Int) {
        genresData[position].gamesList.addAll(games)
        notifyDataSetChanged()
    }
}