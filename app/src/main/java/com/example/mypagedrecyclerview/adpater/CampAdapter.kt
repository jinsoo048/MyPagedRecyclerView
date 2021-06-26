package com.example.mypagedrecyclerview.adpater

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypagedrecyclerview.databinding.ItemRecyclerTourCampBinding
import com.example.mypagedrecyclerview.model.camp.Item

class CampAdapter :
    PagingDataAdapter<Item, CampAdapter.CampViewHolder>(CampComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampViewHolder {
        return CampViewHolder(
            ItemRecyclerTourCampBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CampViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {holder.bindCamp(it)}
    }

    inner class CampViewHolder(private val binding: ItemRecyclerTourCampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindCamp(item: Item) = with(binding) {
            facltNmTv.text = item.facltNm
            indutyTv.text = item.induty
            addr1Tv.text = item.addr1
        }
    }

    object CampComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.contentId == newItem.contentId
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

}