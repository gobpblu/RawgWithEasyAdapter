package com.developer.android.rawg.main.ui.main

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseFragment
import com.developer.android.rawg.databinding.FragmentGameDetailsBinding
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.ui.main.adapter.ScreenshotsAdapter

private const val ARG1_GAME = "game"


class GameDetailsFragment : BaseFragment(R.layout.fragment_game_details) {
    private lateinit var gameDetails: GameType

    private lateinit var binding: FragmentGameDetailsBinding

    private val screenshotsAdapter: ScreenshotsAdapter by lazy {
        ScreenshotsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            gameDetails = arguments?.getParcelable(ARG1_GAME)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        when (gameDetails) {
            is GameType.FullGame -> fullGameBind(gameDetails as GameType.FullGame)
            is GameType.ImageGame -> imageGameBind(gameDetails as GameType.ImageGame)
            is GameType.DescriptionGame -> descriptionGameBind(gameDetails as GameType.DescriptionGame)
        }

    }

    private fun descriptionGameBind(game: GameType.DescriptionGame) = with(binding) {
        binding.textViewGameDetailsName.text = game.name
        textViewReleaseDateValue.text = game.released
        textViewPlatformValues.text = buildString {
            game.parentPlatforms.forEachIndexed { index, platform ->
                append(platform.parentPlatform.name)
                if (index != game.parentPlatforms.lastIndex) append(", ")
            }
        }
        textViewPlaytimeValue.text = buildString { append(game.playTime).append("h") }
    }

    private fun imageGameBind(game: GameType.ImageGame) {
        context?.let { Glide.with(it).load(game.backgroundImage).placeholder(R.drawable.abstract_game).into(binding.imageViewDetailsIcon) }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun fullGameBind(game: GameType.FullGame) = with(binding) {
        context?.let { Glide.with(it).load(game.backgroundImage).placeholder(R.drawable.abstract_game).into(binding.imageViewDetailsIcon) }
        binding.textViewGameDetailsName.text = game.name
        textViewMetascoreValue.text = buildString { append("${game.metaCritic}") }
        setMetascore(game.metaCritic)
        recyclerViewScreenshots.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewScreenshots.adapter = screenshotsAdapter
        screenshotsAdapter.addData(game)
        textViewReleaseDateValue.text = game.released
        textViewUpdatedValue.text = game.updated.substring(0, 10)
        ratingBar.apply {
            rating = game.rating
        }
        textViewPlatformValues.text = buildString {
            game.parentPlatforms.forEachIndexed { index, platform ->
                append(platform.parentPlatform.name)
                if (index != game.parentPlatforms.lastIndex) append(", ")
            }
        }
        textViewPlaytimeValue.text = buildString { append(game.playTime).append("h") }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun setMetascore(metaCritic: Int) = with(binding) {
        val metaCriticColor = when (metaCritic) {
            in 75..100 -> requireContext().getColor(R.color.metascore_green)
            in 50..74 -> requireContext().getColor(R.color.metascore_yellow)
            else -> requireContext().getColor(R.color.metascore_red)
        }
        textViewMetascoreValue.setTextColor(metaCriticColor)
        val drawable: GradientDrawable =
            textViewMetascoreValue.background as GradientDrawable
        drawable.setStroke(1, metaCriticColor)
    }

    override fun onDetach() {
        super.onDetach()
        view?.let { context?.let { it1 -> Glide.with(it1).clear(it) } }
    }

    companion object {
        @JvmStatic
        fun newInstance(game: GameType) =
            GameDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG1_GAME, game)
                }
            }
    }
}