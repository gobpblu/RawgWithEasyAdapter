package com.developer.android.rawg.common.mvp

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.developer.android.rawg.R

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes),
    BaseFragmentContract {
    fun changeFragment(fragment: Fragment, id: Int) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
//            .hide(parentFragmentManager.findFragmentById(R.id.fragmentContainer)!!)
//            .add(id, fragment)
            .replace(id, fragment)
            .commit()
    }



    fun clearBackStack() {
        val manager: FragmentManager = requireActivity().supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}