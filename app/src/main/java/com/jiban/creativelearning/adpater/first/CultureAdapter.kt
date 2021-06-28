package com.jiban.creativelearning.adpater.first

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jiban.creativelearning.databinding.ItemRecyclerFirstCultureBinding
import com.jiban.creativelearning.model.first.culture.Row
import com.jiban.creativelearning.ui.first.CultureFragment

class CultureAdapter(var context: CultureFragment, var cellClickListener: CultureFragment) :
    PagingDataAdapter<Row, CultureAdapter.CultureViewHolder>(CultureComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CultureViewHolder {
        return CultureViewHolder(
            ItemRecyclerFirstCultureBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CultureViewHolder, position: Int) {

        val row = getItem(position)
        row?.let { holder.bindCulture(it) }

        holder.itemView.setOnClickListener {
            if (row != null) {
                cellClickListener.onCellClickListenerCultureEvent(row)
            }
        }
    }

    inner class CultureViewHolder(private val binding: ItemRecyclerFirstCultureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindCulture(row: Row) = with(binding) {

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

    object CultureComparator : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldRow: Row, newRow: Row): Boolean {
            return oldRow.SVCID == newRow.SVCID
        }

        override fun areContentsTheSame(oldRow: Row, newRow: Row): Boolean {
            return oldRow == newRow
        }
    }


}