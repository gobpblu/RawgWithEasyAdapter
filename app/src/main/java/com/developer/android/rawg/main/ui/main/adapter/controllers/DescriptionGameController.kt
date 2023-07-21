package com.developer.android.rawg.main.ui.main.adapter.controllers

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.databinding.GameItemDescriptionBinding
import com.developer.android.rawg.databinding.GameItemImageBinding
import com.developer.android.rawg.main.model.GameType
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class DescriptionGameController : BindableItemController<GameType.DescriptionGame, DescriptionGameController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: GameType.DescriptionGame): Any = data.hashCode()

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<GameType.DescriptionGame>(parent, R.layout.game_item_description) {

        private val binding: GameItemDescriptionBinding = GameItemDescriptionBinding.bind(itemView)

        override fun bind(data: GameType.DescriptionGame) {
            with(binding) {
                textViewName.text = data.name
                textViewReleaseDate.text = buildString { append(" ").append(data.released) }
                textViewPlaytime.text = buildString { append(" ").append(data.playTime).append(" hours") }
                textViewPlatforms.text = buildString {
                    append("Platforms: ")
                    data.parentPlatforms.forEachIndexed { index, platform ->
                        append(platform.parentPlatform.name)
                        if (index != data.parentPlatforms.lastIndex) append(", ")
                    }
                }
            }
        }

    }

}