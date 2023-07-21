package com.developer.android.rawg.main.ui.main.adapter.controllers

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.databinding.GameItemBinding
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.games.ParentPlatformContainer
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import timber.log.Timber

class FullGameController(
    private val onGameItemClicked: (GameType.FullGame) -> Unit,
    ) : BindableItemController<GameType.FullGame, FullGameController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: GameType.FullGame): Any = data.id

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<GameType.FullGame>(parent, R.layout.game_item) {

        private val binding: GameItemBinding = GameItemBinding.bind(itemView)

        private val platforms = listOf(binding.imageViewFirstIcon,
            binding.imageViewSecondIcon,
            binding.imageViewThirdIcon,
            binding.imageViewFourthIcon,
            binding.imageViewFifthIcon,
            binding.imageViewSixthIcon,
            binding.imageViewSeventhIcon,
            binding.imageViewEighthIcon,
            binding.imageViewNinthIcon,
            binding.imageViewTenthIcon)

        override fun bind(data: GameType.FullGame) {
            with(binding) {
                Timber.i("FullGame -> bind()")
                Glide.with(itemView.context).load(data.backgroundImage)
                    .placeholder(R.drawable.abstract_game).into(imageViewIcon)
                textViewName.text = data.name
                textViewItemReleaseDate.text = buildString { append(" ").append(data.released) }
                textViewItemPlaytime.text =
                    buildString { append(" ").append(data.playTime).append(" hours") }
                parentsBind(data.parentPlatforms)
                itemView.setOnClickListener { onGameItemClicked.invoke(data) }
            }
        }

        private fun parentsBind(parentPlatforms: List<ParentPlatformContainer>) = with(binding) {
            var i = 0
            platforms.forEach { it.setImageDrawable(null) }
            parentPlatforms.forEach {
                when (it.parentPlatform.slug) {
                    "pc" -> platforms[i].setImageResource(R.drawable.pc_icon)
                    "playstation" -> platforms[i].setImageResource(R.drawable.playstation_icon)
                    "xbox" -> platforms[i].setImageResource(R.drawable.xbox_icon)
                    "ios" -> platforms[i].setImageResource(R.drawable.ios_icon)
                    "android" -> platforms[i].setImageResource(R.drawable.android_icon)
                    "mac" -> platforms[i].setImageResource(R.drawable.mac_icon)
                    "linux" -> platforms[i].setImageResource(R.drawable.linux_icon)
                    "nintendo" -> platforms[i].setImageResource(R.drawable.nintendo_icon)
                    "atari" -> platforms[i].setImageResource(R.drawable.atari_icon)
                    "commodore-amiga" -> platforms[i].setImageResource(R.drawable.commodore_amiga_icon)
                    "sega" -> platforms[i].setImageResource(R.drawable.sega_icon)
                    "3do" -> platforms[i].setImageResource(R.drawable.do_icon)
                    "neo-geo" -> platforms[i].setImageResource(R.drawable.neo_geo_icon)
                    "web" -> platforms[i].setImageResource(R.drawable.web_icon)
                }
                i++
            }
        }

    }

}