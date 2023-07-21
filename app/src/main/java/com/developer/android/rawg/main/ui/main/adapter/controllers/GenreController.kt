package com.developer.android.rawg.main.ui.main.adapter.controllers

import android.view.ViewGroup
import com.developer.android.rawg.R
import com.developer.android.rawg.databinding.ItemGenreBinding
import com.developer.android.rawg.main.ui.main.MainUi
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class GenreController : BindableItemController<MainUi.Genre, GenreController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: MainUi.Genre): Any = data.name

    inner class Holder(parent: ViewGroup): BindableViewHolder<MainUi.Genre>(parent, R.layout.item_genre) {

        private val binding: ItemGenreBinding = ItemGenreBinding.bind(itemView)

        override fun bind(data: MainUi.Genre) {
            binding.tvTitle.text = data.name
        }

    }

}