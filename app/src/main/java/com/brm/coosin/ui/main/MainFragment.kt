package com.brm.coosin.ui.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.brm.coosin.R
import com.brm.coosin.adapter.ReceiptAdapter
import com.brm.coosin.adapter.SliderAdapter
import com.brm.coosin.data.model.Chef
import com.brm.coosin.data.model.Receipt
import com.brm.coosin.databinding.FragmentMainBinding
import com.brm.coosin.ui.home.HomeFragment
import kotlin.math.abs
import kotlin.random.Random

class MainFragment : HomeFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val handler = Handler()

    private val runner = Runnable {
        if (binding.viewPager2.currentItem >= 3)
            binding.viewPager2.currentItem = 0
        else
            binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        val slideList = arrayListOf(
            Chef(chiefName = "Bred Green", chiefImage = R.drawable.chef_britain),
            Chef(chiefName = "Haputti Asien", chiefImage = R.drawable.chef_indian),
            Chef(chiefName = "Bred Green", chiefImage = R.drawable.chef_britain),
            Chef(chiefName = "Haputti Asien", chiefImage = R.drawable.chef_indian)
        )

        binding.viewPager2.apply {
            adapter = SliderAdapter(binding.viewPager2, slideList)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                run {
                    val r : Float = abs(position)
                    page.scaleY = 0.85f + r * 0.15f
                }
            }
            setPageTransformer(compositePageTransformer)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(runner)
                    handler.postDelayed(runner, 6000)
                }
            })
        }

        binding.dotsIndicator.setViewPager2(binding.viewPager2)

        val list = ArrayList<Receipt>()
        for (i in 0..Random.nextInt(2,5)){
            list.add(Receipt("asd", "asd", "asd", "asd"))
        }
        val adapter1 = ReceiptAdapter()
        adapter1.newList(list)
        val adapter2 = ReceiptAdapter()
        list.clear()
        for (i in 0..Random.nextInt(2,5)){
            list.add(Receipt("asd", "asd", "asd", "asd"))
        }
        adapter2.newList(list)
        val adapter3 = ReceiptAdapter()
        list.clear()
        for (i in 0..Random.nextInt(2,5)){
            list.add(Receipt("asd", "asd", "asd", "asd"))
        }
        adapter3.newList(list)
        binding.bestRecycler.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
        binding.bestRecycler.adapter = adapter1

        binding.cuisineRecycler.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
        binding.cuisineRecycler.adapter = adapter2

        binding.latestRecycler.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.HORIZONTAL, false)
        binding.latestRecycler.adapter = adapter3


        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        handler.removeCallbacks(runner)
        super.onDestroy()
    }
}