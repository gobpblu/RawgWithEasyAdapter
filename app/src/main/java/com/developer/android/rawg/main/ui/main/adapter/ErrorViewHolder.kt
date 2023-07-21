package com.developer.android.rawg.main.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.databinding.GameItemErrorBinding

class ErrorViewHolder(
    private val binding: GameItemErrorBinding,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
    ) : this(
        GameItemErrorBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)
    )

    fun bind(state: PagingState.Error, onFailedListener: () -> Unit) {

    }
}