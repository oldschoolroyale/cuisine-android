package com.brm.coosin.ui.main

import android.os.Bundle
import android.view.MenuItem
import com.brm.coosin.R
import com.brm.coosin.databinding.ActivityMainBinding
import com.brm.coosin.ui.favourite.FavouriteFragment
import com.brm.coosin.ui.home.HomeActivity
import com.brm.coosin.ui.home.HomeView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : HomeActivity(), HomeView, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(MainFragment(), R.id.fragment_container, true)
        binding.bottomNavLayout.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> {
                if (getVisibleFragment() !is MainFragment){
                    changeFragment(MainFragment(),
                        R.id.fragment_container, true)
                    return true
                }
            }
            R.id.favorite ->{
                if (getVisibleFragment() !is FavouriteFragment){
                    changeFragment(FavouriteFragment(),
                        R.id.fragment_container, true)
                    return true
                }
            }
        }
        return false
    }
}