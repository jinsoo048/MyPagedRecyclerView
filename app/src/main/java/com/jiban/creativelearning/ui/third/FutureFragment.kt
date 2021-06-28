package com.jiban.creativelearning.ui.third

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jiban.creativelearning.adpater.first.CampLoadStateAdapter
import com.jiban.creativelearning.adpater.third.FutureAdapter
import com.jiban.creativelearning.api.third.FutureApi
import com.jiban.creativelearning.model.third.future.Row
import com.jiban.creativelearning.viewmodels.third.FutureViewModel
import com.jiban.creativelearning.viewmodels.third.FutureViewModelFactory
import com.jiban.creativelearning.x.config.BaseFragment
import com.jiban.creativelearning.R
import com.jiban.creativelearning.databinding.FragmentFutureBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FutureFragment :
    BaseFragment<FragmentFutureBinding>(FragmentFutureBinding::bind, R.layout.fragment_future) {

    private lateinit var viewModel: FutureViewModel
    private var activity: Activity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onGetFutureSuccess()
    }

    private fun onGetFutureSuccess() {

        val factory = FutureViewModelFactory(FutureApi())
        viewModel = ViewModelProvider(this, factory).get(FutureViewModel::class.java)

        val futureAdapter = FutureAdapter(context = FutureFragment(), this)
        //binding.futureRv.layoutManager = LinearLayoutManager(requireContext())
        binding.futureRv.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        binding.futureRv.setHasFixedSize(true)

        binding.futureRv.adapter = futureAdapter.withLoadStateHeaderAndFooter(
            header = CampLoadStateAdapter { futureAdapter.retry() },
            footer = CampLoadStateAdapter { futureAdapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.rows.collectLatest { pagedData ->
                futureAdapter.submitData(pagedData)
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
    fun onCellClickListenerFutureEvent(response: Row) {
        var mUrl = "https://www.bing.com/search?q=" + response.HT_NM
        activity !!.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mUrl)))

    }
}