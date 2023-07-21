package com.developer.android.rawg.main.ui.main.adapter.controllers

import android.view.ViewGroup
import androidx.core.view.doOnDetach
import androidx.core.view.doOnNextLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.R
import com.developer.android.rawg.databinding.ItemGamesBinding
import com.developer.android.rawg.main.model.GameType
import com.developer.android.rawg.main.ui.main.MainUi
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import timber.log.Timber

class GamesController(
    private val onGameItemClicked: (GameType.FullGame) -> Unit,
    private val onLoadGames: (genre: String, page: Int, itemPosition: Int) -> Unit,
    ) : BindableItemController<MainUi.GamesList, GamesController.Holder>() {

    override fun getItemId(data: MainUi.GamesList): Any = data.genre

    override fun createViewHolder(parent: ViewGroup): Holder {
        Timber.d("I work")
        return Holder(parent)
    }

    inner class Holder(parent: ViewGroup) : BindableViewHolder<MainUi.GamesList>(parent, R.layout.item_games) {

        private val binding = ItemGamesBinding.bind(itemView)

        private val footerController = GamesFooterPaginationController()

        private lateinit var data: MainUi.GamesList

        private val adapter = EasyPaginationAdapter(footerController) {
            val layoutManager = binding.rvGenre.layoutManager as? LinearLayoutManager ?: return@EasyPaginationAdapter
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//            visibleItemPosition = firstVisibleItemPosition
            Timber.d("firstVisible: $firstVisibleItemPosition")
            onLoadGames(data.genre, data.games.nextPage, firstVisibleItemPosition)
        }
        private val fullGameController = FullGameController(onGameItemClicked)
        private val imageGameController = ImageGameController()
        private val descriptionGameController = DescriptionGameController()
        var visibleItemPosition = 0


        init {
            Timber.d("INIT BLOCK AGAIN")
            binding.rvGenre.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
                    visibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    Timber.d("firstVisibleItemPosition: $visibleItemPosition")
                }
            })
            binding.rvGenre.adapter = adapter

        }

        override fun bind(data: MainUi.GamesList) {
            setAdapterItems(data.games)
            binding.rvGenre.scrollToPosition(data.lastVisiblePosition)
            this.data = data
            if (data.page == 1) onLoadGames(data.genre, data.page, 0)
        }

        private fun setAdapterItems(games: List<GameType>) {
            val itemList = ItemList.create()
                for (game in games) {
                    when (game) {
                        is GameType.FullGame -> itemList.add(game, fullGameController)
                        is GameType.ImageGame -> itemList.add(game, imageGameController)
                        is GameType.DescriptionGame -> itemList.add(game, descriptionGameController)
                    }
            }
            adapter.setItems(itemList, PaginationState.READY)
        }

    }



}