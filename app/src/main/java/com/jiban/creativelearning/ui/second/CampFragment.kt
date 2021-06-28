package com.jiban.creativelearning.ui.second

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jiban.creativelearning.adpater.first.CampLoadStateAdapter
import com.jiban.creativelearning.adpater.second.CampAdapter
import com.jiban.creativelearning.api.second.CampApi
import com.jiban.creativelearning.viewmodels.second.CampViewModel
import com.jiban.creativelearning.viewmodels.second.CampViewModelFactory
import com.jiban.creativelearning.x.config.BaseFragment
import com.jiban.creativelearning.R
import com.jiban.creativelearning.databinding.FragmentCampBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CampFragment :
    BaseFragment<FragmentCampBinding>(FragmentCampBinding::bind, R.layout.fragment_camp) {

    private lateinit var viewModel: CampViewModel
    private var activity: Activity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onGetCampSuccess()
    }

    private fun onGetCampSuccess() {

        val factory = CampViewModelFactory(CampApi())
        viewModel = ViewModelProvider(this, factory).get(CampViewModel::class.java)

        val campAdapter = CampAdapter(context = CampFragment(),this)
        //binding.campRv.layoutManager = LinearLayoutManager(requireContext())
        binding.campRv.layoutManager = GridLayoutManager(context, 2)

        binding.campRv.setHasFixedSize(true)

        binding.campRv.adapter = campAdapter.withLoadStateHeaderAndFooter(
            header = CampLoadStateAdapter { campAdapter.retry() },
            footer = CampLoadStateAdapter { campAdapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.items.collectLatest { pagedData ->
                campAdapter.submitData(pagedData)
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
    fun onCellClickListenerCampEvent(response: com.jiban.creativelearning.model.second.camp.Item?) {
        var mUrl = "https://www.bing.com/search?q=" + response !!.facltNm
        activity !!.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mUrl)))

    }
}