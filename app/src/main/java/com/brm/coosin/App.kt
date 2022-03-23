package com.brm.coosin

import android.app.Application
import com.brm.coosin.util.AppPreferences

/**
 * Created by Rakhimjonov Shokhsulton on 22,март,2022
 * at Mayasoft LLC,
 * Tashkent, UZB.
 */
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        AppPreferences.setUp(this)
    }
}