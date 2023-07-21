package com.developer.android.rawg.main.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.databinding.GameItemBinding
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.games.ParentPlatformContainer
import timber.log.Timber

class MainViewHolder(
    private val binding: GameItemBinding,
    private val onGameItemClicked: (GameType.FullGame) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        onGameItemClicked: (GameType.FullGame) -> Unit,
    ) : this(
        GameItemBinding.inflate(LayoutInflater.from(
            parent.context), parent, false),
        onGameItemClicked)


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


    fun bind(item: GameType.FullGame){
        with(binding) {
            Timber.i("MainViewHolder")
            Glide.with(itemView.context).load(item.backgroundImage)
                .placeholder(R.drawable.abstract_game).into(imageViewIcon)
            textViewName.text = item.name
            textViewItemReleaseDate.text = buildString { append(" ").append(item.released) }
            textViewItemPlaytime.text =
                buildString { append(" ").append(item.playTime).append(" hours") }
            parentsBind(item.parentPlatforms)
            itemView.setOnClickListener { onGameItemClicked.invoke(item) }
        }
    }

    fun detach() {
        Glide.with(itemView).clear(binding.imageViewIcon)
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