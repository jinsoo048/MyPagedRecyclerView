package com.jiban.creativelearning.adpater.second

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jiban.creativelearning.databinding.ItemRecyclerSecondTravelBinding
import com.jiban.creativelearning.model.second.travel.Item
import com.jiban.creativelearning.ui.second.TravelFragment

class TravelAdapter(var context: TravelFragment, var cellClickListener: TravelFragment) :
    PagingDataAdapter<Item, TravelAdapter.TravelViewHolder>(TravelComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        return TravelViewHolder(
            ItemRecyclerSecondTravelBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {

        val item = getItem(position)
        item?.let { holder.bindTravel(it) }

        holder.itemView.setOnClickListener {
            if (item != null) {
                cellClickListener.onCellClickListenerTravelEvent(item)
            }
        }
    }

    inner class TravelViewHolder(private val binding: ItemRecyclerSecondTravelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindTravel(item: Item) = with(binding) {

            Glide.with(imageIv.context)
                .load(item.galWebImageUrl)
                .into(imageIv)

            galTitleTv.text = item.galTitle
            galPhotographyLocationTv.text = item.galPhotographyLocation
            galViewCountTv.text = item.galViewCount.toString()
            galSearchKeywordTv.text = item.galSearchKeyword
        }
    }

    object TravelComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.galContentId == newItem.galContentId
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }


}