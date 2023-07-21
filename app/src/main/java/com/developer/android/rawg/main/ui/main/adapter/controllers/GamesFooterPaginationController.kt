package com.developer.android.rawg.main.ui.main.adapter.controllers

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.developer.android.rawg.R
import com.developer.android.rawg.databinding.LayoutPaginationFooterBinding
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState

class GamesFooterPaginationController :
    EasyPaginationAdapter.BasePaginationFooterController<GamesFooterPaginationController.Holder>() {

    override fun createViewHolder(
        parent: ViewGroup,
        listener: EasyPaginationAdapter.OnShowMoreListener
    ): Holder {
        return Holder(parent, listener)
    }

    inner class Holder(
        parent: ViewGroup,
        listener: EasyPaginationAdapter.OnShowMoreListener
    ) : EasyPaginationAdapter.BasePaginationFooterHolder(
        parent,
        R.layout.layout_pagination_footer
    ) {

        private val binding: LayoutPaginationFooterBinding =
            LayoutPaginationFooterBinding.bind(itemView)

        init {
            with(binding) {
                tvShowMore.setOnClickListener { listener.onShowMore() }
                progressBar.visibility = View.GONE
                tvShowMore.visibility = View.GONE
            }
        }

        override fun bind(state: PaginationState) {

            with(binding) {
                when (state) {
                    PaginationState.READY -> {

                        progressBar.visibility = View.VISIBLE
                        tvShowMore.visibility = View.GONE
                    }

                    PaginationState.COMPLETE -> {
                        progressBar.visibility = View.GONE
                        tvShowMore.visibility = View.GONE
                    }

                    PaginationState.ERROR -> {
                        progressBar.visibility = View.GONE
                        tvShowMore.visibility = View.VISIBLE
                    }

                    else -> throw IllegalArgumentException("unsupported state: $state")
                }
            }
        }
    }
}