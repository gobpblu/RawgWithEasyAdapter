package com.developer.android.rawg.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.android.rawg.R
import com.developer.android.rawg.common.extensions.hideAndAddFragment
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.databinding.FragmentAllGamesBinding
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.model.genres.Genre
import com.developer.android.rawg.main.model.genres.Genres
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllGamesFragment : Fragment() {

//    override val presenter: MainContract.Presenter by inject()

    private val binding: FragmentAllGamesBinding by lazy {
        FragmentAllGamesBinding.inflate(layoutInflater)
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            super.onViewCreated(view, savedInstanceState)

            if (rvOuter.layoutManager != linearLayoutManager)
                rvOuter.apply {
                    layoutManager = linearLayoutManager
                }
//            presenter.getGenres()

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.genresPagingData.collectLatest {
//                        Timber.d("$it")
//                        genresAdapter.submitData(it)
//                    }
                }
            }

            /*swipeRefresh.setDistanceToTriggerSync(5)
            swipeRefresh.setProgressViewEndTarget(false, 50)
            swipeRefresh.setOnRefreshListener {
                for (index in adaptersList.indices) {
                    adaptersList[index].clearItems()
                    presenter.refresh(index, genres = GamesGenres.values()[index].genre)
                }
            }*/

        }
    }

    fun showGenres(genres: Genres) {
//        genresAdapter.setGenres(genres.gamesGenres)
    }

    fun showGames(games: List<GameType?>, position: Int) {
//        genresAdapter.setGames(games, position)
    }

    private fun getGamesByGenre(genre: Genre) {
//        viewModel
//        presenter.getGames(gameGenre)
    }

    private fun onGameItemClicked(gameDetails: GameType.FullGame) {
//        val fragment = GameDetailsFragment.newInstance(gameDetails)
//        parentFragmentManager.findFragmentById(R.id.fragmentContainer)?.let {
//            it.hideAndAddFragment(
//                addFragment = fragment,
//                id = R.id.fragmentContainer
//            )
//        }
    }

    private fun onFailedListener() {
        Toast.makeText(context, R.string.loading_error, Toast.LENGTH_SHORT)
            .show()
    }

    fun showRefreshing(isRefreshing: Boolean) {
//        binding.swipeRefresh.isRefreshing = isRefreshing
    }

     fun showErrorMessage(e: Throwable) {
        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
    }

    fun showPagingState(pagingState: PagingState, position: Int) {
//        mainAdapter.setPagingState(pagingState, position)
    }

}