package com.jiban.creativelearning.adpater.third

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jiban.creativelearning.databinding.ItemRecyclerThirdLiveBinding
import com.jiban.creativelearning.model.third.live.Row
import com.jiban.creativelearning.ui.third.LiveFragment

class LiveAdapter(var context: LiveFragment, var cellClickListener: LiveFragment) :
    PagingDataAdapter<Row, LiveAdapter.LiveViewHolder>(LiveComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveViewHolder {
        return LiveViewHolder(
            ItemRecyclerThirdLiveBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {

        val item = getItem(position)
        item?.let { holder.bindLive(it) }

        holder.itemView.setOnClickListener {
            if (item != null) {
                cellClickListener.onCellClickListenerLiveEvent(item)
            }
        }
    }

    inner class LiveViewHolder(private val binding: ItemRecyclerThirdLiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindLive(item: Row) = with(binding) {

            CourseNameTv.text = item.COURSE_NAME
            AreaGuTv.text = item.AREA_GU
            DistanceTv.text = item.DISTANCE
            LeadTimeTv.text = item.LEAD_TIME
            SubwayTv.text = item.RELATE_SUBWAY
            LevelTv.text = item.COURSE_LEVEL
            DetailCourseTv.text = item.DETAIL_COURSE
            ContentTv.text = item.CONTENT
        }
    }

    object LiveComparator : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.CODE_NAME == newItem.CODE_NAME
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            TODO("Not yet implemented")
        }
    }


}