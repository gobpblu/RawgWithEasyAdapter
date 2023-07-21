package com.developer.android.rawg.main.ui.main.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.developer.android.rawg.main.api.model.genres.GenreDetailsResponse
import com.developer.android.rawg.main.model.games.Genre

class GenreAdapter(diffCallback: DiffUtil.ItemCallback<GenreDetailsResponse>) :
    PagingDataAdapter<GenreDetailsResponse, GenreViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreViewHolder {
        return GenreViewHolder(parent, {}, {}, {})
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        item?.let { holder.bind(it) }
    }
}

object GenreComparator : DiffUtil.ItemCallback<GenreDetailsResponse>() {
    override fun areItemsTheSame(oldItem: GenreDetailsResponse, newItem: GenreDetailsResponse): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GenreDetailsResponse, newItem: GenreDetailsResponse): Boolean {
        return oldItem == newItem
    }
}