package com.brm.coosin.ui.home

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface HomeView : MvpView{
    fun onRequestStart()
    fun onRequestComplete()
    fun onRequestError(message: Any)
    fun changeFragment(newFragment: Fragment, fragmentId: Int, hasAnimation: Boolean)
}