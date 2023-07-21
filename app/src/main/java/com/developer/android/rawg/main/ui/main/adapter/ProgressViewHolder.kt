package com.developer.android.rawg.main.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.databinding.ProgressBarBinding

class ProgressViewHolder(
    private val binding: ProgressBarBinding,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
    ) : this(
        ProgressBarBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)
    )

}