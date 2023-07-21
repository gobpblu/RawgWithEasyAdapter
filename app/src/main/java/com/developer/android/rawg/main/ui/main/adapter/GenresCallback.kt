package com.developer.android.rawg.main.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.developer.android.rawg.main.model.genres.GameGenre

class GenresCallback(private val oldList: List<GameGenre>, private val newList: List<GameGenre>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].slug == newList[newItemPosition].slug &&
                oldList[oldItemPosition].gamesList == newList[newItemPosition].gamesList &&
                oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}