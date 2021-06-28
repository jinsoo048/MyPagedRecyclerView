package com.jiban.creativelearning.adpater.second

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jiban.creativelearning.databinding.ItemRecyclerSecondCampBinding
import com.jiban.creativelearning.model.second.camp.Item
import com.jiban.creativelearning.ui.second.CampFragment

class CampAdapter(var context: CampFragment, var cellClickListener: CampFragment) :
    PagingDataAdapter<Item, CampAdapter.CampViewHolder>(CampComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampViewHolder {
        return CampViewHolder(
            ItemRecyclerSecondCampBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CampViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {holder.bindCamp(it)}

        holder.itemView.setOnClickListener {
            if (item != null) {
                cellClickListener.onCellClickListenerCampEvent(item)
            }
        }
    }

    inner class CampViewHolder(private val binding: ItemRecyclerSecondCampBinding) :
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