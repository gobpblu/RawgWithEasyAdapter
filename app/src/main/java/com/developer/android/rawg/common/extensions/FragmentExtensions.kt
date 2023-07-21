package com.developer.android.rawg.common.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

fun Fragment.hideAndAddFragment(addFragment: Fragment, id: Int) {
    val fragmentManager = parentFragmentManager
    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.addToBackStack(null)
        .hide(this)
        .add(id, addFragment)
        .commit()
}