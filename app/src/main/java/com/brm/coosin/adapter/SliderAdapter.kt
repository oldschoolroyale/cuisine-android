package com.brm.coosin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.brm.coosin.R
import com.brm.coosin.data.model.Chef

/**
 * Created by Rakhimjonov Shokhsulton on 25,март,2022
 * at Mayasoft LLC,
 * Tashkent, UZB.
 */
class SliderAdapter(val viewPager2: ViewPager2, private var dataList: List<Chef>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun newList(list: List<Chef>){
        dataList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SlideViewPager(LayoutInflater.from(parent.context)
            .inflate(R.layout.slide_item_container, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SlideViewPager)
            holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    private class SlideViewPager(view:View) : RecyclerView.ViewHolder(view){

        fun bind(chef: Chef){
            itemView.findViewById<ImageView>(R.id.image_slide)
                .setImageResource(chef.chiefImage)
        }
    }
}