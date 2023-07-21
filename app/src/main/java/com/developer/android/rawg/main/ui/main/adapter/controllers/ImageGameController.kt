package com.developer.android.rawg.main.ui.main.adapter.controllers

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.databinding.GameItemImageBinding
import com.developer.android.rawg.main.model.GameType
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class ImageGameController : BindableItemController<GameType.ImageGame, ImageGameController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: GameType.ImageGame): Any = data.hashCode()

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<GameType.ImageGame>(parent, R.layout.game_item_image) {

        private val binding: GameItemImageBinding = GameItemImageBinding.bind(itemView)

        override fun bind(data: GameType.ImageGame) {
            Glide.with(itemView.context).load(data.backgroundImage).placeholder(R.drawable.abstract_game).into(binding.imageViewIcon)
        }

    }

}