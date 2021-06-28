package com.jiban.creativelearning.adpater.third

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jiban.creativelearning.databinding.ItemRecyclerThirdFutureBinding
import com.jiban.creativelearning.model.third.future.Row
import com.jiban.creativelearning.ui.third.FutureFragment

class FutureAdapter(var context: FutureFragment, var cellClickListener: FutureFragment) :
    PagingDataAdapter<Row, FutureAdapter.FutureViewHolder>(FutureComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureViewHolder {
        return FutureViewHolder(
            ItemRecyclerThirdFutureBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FutureViewHolder, position: Int) {

        val item = getItem(position)
        item?.let { holder.bindFuture(it) }

        holder.itemView.setOnClickListener {
            if (item != null) {
                cellClickListener.onCellClickListenerFutureEvent(item)
            }
        }
    }

    inner class FutureViewHolder(private val binding: ItemRecyclerThirdFutureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindFuture(item: Row) = with(binding) {

            Glide.with(AcpNameIv.context)
                .load(item.ACP_THUMB_NAME)
                .into(AcpNameIv)

            NameTv.text = item.HT_NM
            TitleTv.text = item.AC_TITLE
            AddressTv.text = item.HT_NEW_ADDR
            TakeTimeTv.text = item.AC_TAKE_TIME

        }
    }

    object FutureComparator : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.HT_NM == newItem.HT_NM
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            TODO("Not yet implemented")
        }
    }


}