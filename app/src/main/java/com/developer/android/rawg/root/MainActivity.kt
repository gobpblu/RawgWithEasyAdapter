package com.developer.android.rawg.root

import android.os.Bundle
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseActivity
import com.developer.android.rawg.main.ui.main.AllGamesFragment
import com.developer.android.rawg.main.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val allGamesFragment = MainFragment()
        changeFragment(allGamesFragment, R.id.fragmentContainer)
    }
}