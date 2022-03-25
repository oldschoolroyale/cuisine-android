package com.brm.coosin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.brm.coosin.R
import com.brm.coosin.adapter.ReceiptAdapter
import com.brm.coosin.data.model.Receipt
import com.brm.coosin.databinding.FragmentMainBinding
import com.brm.coosin.ui.home.HomeFragment
import kotlin.random.Random

class MainFragment : HomeFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

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
        super.onDestroy()
    }
}