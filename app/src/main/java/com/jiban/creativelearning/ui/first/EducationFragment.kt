package com.jiban.creativelearning.ui.first

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import com.jiban.creativelearning.adpater.first.CampLoadStateAdapter
import com.jiban.creativelearning.adpater.first.EducationAdapter
import com.jiban.creativelearning.api.first.EducationApi
import com.jiban.creativelearning.model.first.education.Row
import com.jiban.creativelearning.viewmodels.first.EducationViewModel
import com.jiban.creativelearning.viewmodels.first.EducationViewModelFactory
import com.jiban.creativelearning.databinding.FragmentEducationBinding
import kotlinx.coroutines.flow.collectLatest


class EducationFragment : Fragment() {

    private lateinit var viewModel: EducationViewModel
    private lateinit var binding: FragmentEducationBinding
    private var activity: Activity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentEducationBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = EducationViewModelFactory(EducationApi())
        viewModel = ViewModelProvider(this, factory).get(EducationViewModel::class.java)

        val educationAdapter = EducationAdapter(context = EducationFragment(), this)
        binding.educationRv.layoutManager = GridLayoutManager(context,2)

        binding.educationRv.setHasFixedSize(true)

        binding.educationRv.adapter = educationAdapter.withLoadStateHeaderAndFooter(
            header = CampLoadStateAdapter { educationAdapter.retry() },
            footer = CampLoadStateAdapter { educationAdapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.rows.collectLatest { pagedData ->
                educationAdapter.submitData(pagedData)
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity) {
            activity = context
        }
    }
    
    @SuppressLint("UseRequireInsteadOfGet")
    fun onCellClickListenerEducationEvent(response: Row) {

        if (activity != null && isAdded) {
            activity !!.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(response.SVCURL)),
                Bundle()
            )

        } else {
            Log.d(
                "JJS",
                "EducationFragment/onCellClickListenerEducationEvent: activity is null !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
            )
        }


    }
    
}