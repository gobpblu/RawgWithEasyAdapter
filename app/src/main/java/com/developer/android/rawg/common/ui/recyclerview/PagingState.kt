package com.developer.android.rawg.common.ui.recyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PagingState : Parcelable {
    object Idle : PagingState()
    object Loading : PagingState()
    object InitialLoading : PagingState()
    class Error(val e: Throwable): PagingState()
}