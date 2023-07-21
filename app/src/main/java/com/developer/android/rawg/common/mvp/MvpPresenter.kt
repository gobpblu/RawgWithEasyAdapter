package com.developer.android.rawg.common.mvp

interface MvpPresenter<V : MvpView> {
    fun attach(view: V)
    fun detach()
}