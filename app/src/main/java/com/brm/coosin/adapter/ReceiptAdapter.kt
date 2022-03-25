package com.brm.coosin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brm.coosin.R
import com.brm.coosin.data.model.Receipt
import kotlin.random.Random

class ReceiptAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = emptyList<Receipt>()

    fun newList(list: List<Receipt>){
        dataList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReceiptViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.receipt_custom_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ReceiptViewHolder){

        }
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    private class ReceiptViewHolder(view:View) : RecyclerView.ViewHolder(view){

    }
}