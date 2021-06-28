package com.jiban.creativelearning.adpater.third

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jiban.creativelearning.databinding.ItemRecyclerThirdDosungBinding
import com.jiban.creativelearning.model.third.dosung.Row
import com.jiban.creativelearning.ui.third.DosungFragment

class DosungAdapter(var context: DosungFragment, var cellClickListener: DosungFragment) :
    PagingDataAdapter<Row, DosungAdapter.DosungViewHolder>(DosungComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DosungViewHolder {
        return DosungViewHolder(
            ItemRecyclerThirdDosungBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DosungViewHolder, position: Int) {

        val item = getItem(position)
        item?.let { holder.bindDosung(it) }

        holder.itemView.setOnClickListener {
            if (item != null) {
                cellClickListener.onCellClickListenerDosungEvent(item)
            }
        }
    }

    inner class DosungViewHolder(private val binding: ItemRecyclerThirdDosungBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindDosung(item: Row) = with(binding) {

            CourseNameTv.text = item.COURSE_NAME
            AreaGuTv.text = item.AREA_GU
            DistanceTv.text = item.DISTANCE.toString()
            LeadTimeTv.text = item.LEAD_TIME
            SubwayTv.text = item.RELATE_SUBWAY
            LevelTv.text = item.COURSE_LEVEL
            DetailCourseTv.text = item.DETAIL_COURSE
        }
    }

    object DosungComparator : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.CODE_NAME == newItem.CODE_NAME
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            TODO("Not yet implemented")
        }
    }


}