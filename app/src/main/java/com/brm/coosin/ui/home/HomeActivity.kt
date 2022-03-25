package com.brm.coosin.ui.home

import android.view.View
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.brm.coosin.R

open class HomeActivity : MvpAppCompatActivity(), HomeView{
    override fun onRequestStart() {

    }

    override fun onRequestComplete() {

    }

    override fun onRequestError(message: Any) {

    }

    override fun changeFragment(newFragment: Fragment, fragmentId: Int, hasAnimation: Boolean) {
        val ft = supportFragmentManager.beginTransaction()
        if (hasAnimation)
            ft.setCustomAnimations(
            R.anim.slide_in_left, R.anim.slide_out_left,
            R.anim.slide_out_right, R.anim.slide_in_right)
        val container: View = findViewById(fragmentId)
        container.visibility = View.VISIBLE
        ft.replace(fragmentId, newFragment)
        ft.addToBackStack(newFragment.javaClass.simpleName)
        ft.commit()
    }

    override fun onBackPressed() {
        val currentFragment: Fragment? = getVisibleFragment()
        val backstackCount = supportFragmentManager.backStackEntryCount
        if (currentFragment == null || backstackCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
            if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
    }

    open fun getVisibleFragment(): Fragment? {
        val fragmentManager = supportFragmentManager
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible) return fragment
        }
        return null
    }
}