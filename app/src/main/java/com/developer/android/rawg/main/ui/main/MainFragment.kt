package com.developer.android.rawg.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.developer.android.rawg.R
import com.developer.android.rawg.common.extensions.hideAndAddFragment
import com.developer.android.rawg.databinding.FragmentAllGamesBinding
import com.developer.android.rawg.main.model.games.FullGame
import com.developer.android.rawg.main.ui.main.adapter.controllers.GamesController
import com.developer.android.rawg.main.ui.main.adapter.controllers.GenreController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val binding: FragmentAllGamesBinding by lazy {
        FragmentAllGamesBinding.inflate(layoutInflater)
    }

    private val easyAdapter = EasyAdapter()

    private val gamesController = GamesController(
        onGameItemClicked = { onGameItemClicked(it) },
        onLoadGames = { genre, page, position -> viewModel.loadGames(genre, page, position) }
    )

    private val genreController = GenreController()

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bind()
    }

    fun initViews() {
        with(binding) {
            rvOuter.adapter = easyAdapter

        }
    }

    fun bind() {
        with(viewModel) {

            observe(mainUi) { mainUiList ->

                val itemList = ItemList.create()

                mainUiList.forEach { item ->
                    when(item) {
                        is MainUi.GamesList -> itemList.add(item, gamesController)
                        is MainUi.Genre -> itemList.add(item, genreController)
                    }
                }

                easyAdapter.setItems(itemList)

//                ItemList.create()
//                    .apply { add(games, gamesController) }
//                    .also { easyAdapter.setItems(it) }

            }

            observe(loading) { isLoading ->
                binding.progressBar.isVisible = isLoading
                binding.rvOuter.isVisible = !isLoading
            }
        }
    }

    private fun onGameItemClicked(gameDetails: FullGame) {
        val fragment = GameDetailsFragment.newInstance(gameDetails)
        parentFragmentManager.findFragmentById(R.id.fragmentContainer)?.let {
            it.hideAndAddFragment(
                addFragment = fragment,
                id = R.id.fragmentContainer
            )
        }
    }


    fun <T : Any, F : Flow<T>> observe(flow: F, body: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) { flow.collect { body(it) } }
        }
    }

}