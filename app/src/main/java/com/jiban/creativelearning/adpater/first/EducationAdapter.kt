package com.jiban.creativelearning.adpater.first

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jiban.creativelearning.databinding.ItemRecyclerFirstEducationBinding
import com.jiban.creativelearning.model.first.education.Row
import com.jiban.creativelearning.ui.first.EducationFragment


class EducationAdapter(var context: EducationFragment, var cellClickListener: EducationFragment) :
        PagingDataAdapter<com.jiban.creativelearning.model.first.education.Row, EducationAdapter.EducationViewHolder>(EducationComparator) {

    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        return EducationViewHolder(
            ItemRecyclerFirstEducationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val row = getItem(position)
        row?.let {holder.bindEducation(it)}

        holder.itemView.setOnClickListener {
            if (row != null) {
                cellClickListener.onCellClickListenerEducationEvent(row)
            }
        }
    }

    inner class EducationViewHolder(private val binding: ItemRecyclerFirstEducationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindEducation(row: Row) = with(binding) {

            Glide.with(imageIv.context)
                .load(row.IMGURL)
                .into(imageIv)

            val match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]".toRegex()
            var str: String = row.SVCNM
            str = str.replace(match, "")

            eventNameTv.text = str

            classKindTv.text = row.MINCLASSNM
            classKindTv.text = row.MAXCLASSNM
            beginTv.text = row.SVCOPNBGNDT
            endTv.text = row.SVCOPNENDDT
            regionTv.text = row.AREANM
            whereTv.text = row.PLACENM
            paymentTv.text = row.PAYATNM
        }
    }

    object EducationComparator : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldRow: Row, newRow: Row): Boolean {
            return oldRow.SVCID == newRow.SVCID
        }

        override fun areContentsTheSame(oldRow: Row, newRow: Row): Boolean {
            return oldRow == newRow
        }
    }
}