package com.developer.android.rawg.main.ui.main.adapter

import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.R
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.GameType
import kotlin.Int


class NestedAdapter(
    private val onGameItemClicked: (GameType.FullGame) -> Unit,
    private val onFailedListener: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<GameType.FullGame>()
    private var pagingState: PagingState = PagingState.Idle

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            R.layout.game_item -> MainViewHolder(parent, onGameItemClicked)
            R.layout.progress_bar -> ProgressViewHolder(parent)
            R.layout.game_item_error -> ErrorViewHolder(parent)
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(item = data[position] as GameType.FullGame)
            is ErrorViewHolder -> {
                val state = pagingState
                if (state is PagingState.Error) holder.bind(state, onFailedListener)
            }
            is ProgressViewHolder -> Unit
        }
        val anim: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.all_games_animation)
        holder.itemView.startAnimation(anim)
    }

    fun setPagingState(newState: PagingState) {
        if (pagingState::class.java == newState::class.java) return
        val shouldHasExtraItem = newState !is PagingState.Idle
        val hasExtraItem = pagingState !is PagingState.Idle

        pagingState = newState

        val count = itemCount

        when {
            shouldHasExtraItem && hasExtraItem -> notifyItemChanged(count - 1)
            !shouldHasExtraItem && hasExtraItem -> notifyItemRemoved(count - 1)
            shouldHasExtraItem && !hasExtraItem -> notifyItemInserted(count)
        }
    }

    override fun getItemViewType(position: Int): Int = when {
        pagingState is PagingState.Idle || position < itemCount - 1 -> R.layout.game_item
        pagingState is PagingState.Loading || pagingState is PagingState.InitialLoading -> R.layout.progress_bar
        else -> R.layout.game_item_error
    }

    override fun getItemCount() = data.size

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is MainViewHolder -> holder.detach()
        }
    }

    fun setItems(newList: List<GameType.FullGame>) {
        val oldList = ArrayList(data)
        data.clear()
        data.addAll(newList)
        DiffUtil.calculateDiff(getDiffCallback(oldList, newList)).dispatchUpdatesTo(this)
    }

    private fun getDiffCallback(
        oldList: List<GameType.FullGame>,
        newList: List<GameType.FullGame>,
    ) = object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return old == new
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size
    }

    fun clearItems() {
        val itemsCount = data.size
        data.clear()
        notifyItemRangeRemoved(0, itemsCount)
    }

}