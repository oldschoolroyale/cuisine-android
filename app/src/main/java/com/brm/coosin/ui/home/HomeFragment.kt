package com.brm.coosin.ui.home

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment

open class HomeFragment: MvpAppCompatFragment(), HomeView {
    override fun onRequestStart() {

    }

    override fun onRequestComplete() {

    }

    override fun onRequestError(message: Any) {

    }

    override fun changeFragment(newFragment: Fragment, fragmentId: Int, hasAnimation: Boolean) {
        if (activity is HomeActivity)
            (activity as HomeActivity).changeFragment(newFragment, fragmentId, hasAnimation)
    }
}