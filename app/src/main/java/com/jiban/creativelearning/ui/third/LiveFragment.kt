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
import androidx.recyclerview.widget.GridLayoutManager
import com.jiban.creativelearning.R
import com.jiban.creativelearning.adpater.first.CampLoadStateAdapter
import com.jiban.creativelearning.adpater.third.LiveAdapter
import com.jiban.creativelearning.api.third.LiveApi
import com.jiban.creativelearning.databinding.FragmentLiveBinding
import com.jiban.creativelearning.model.third.live.Row
import com.jiban.creativelearning.viewmodels.third.LiveViewModel
import com.jiban.creativelearning.viewmodels.third.LiveViewModelFactory
import com.jiban.creativelearning.x.config.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LiveFragment :
    BaseFragment<FragmentLiveBinding>(FragmentLiveBinding::bind, R.layout.fragment_live) {

    private lateinit var viewModel: LiveViewModel
    private var activity: Activity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onGetLiveSuccess()
    }

    private fun onGetLiveSuccess() {

        val factory = LiveViewModelFactory(LiveApi())
        viewModel = ViewModelProvider(this, factory).get(LiveViewModel::class.java)

        val liveAdapter = LiveAdapter(context = LiveFragment(), this)
        //binding.liveRv.layoutManager = LinearLayoutManager(requireContext())
        binding.liveRv.layoutManager = GridLayoutManager(context, 2)

        binding.liveRv.setHasFixedSize(true)

        binding.liveRv.adapter = liveAdapter.withLoadStateHeaderAndFooter(
            header = CampLoadStateAdapter { liveAdapter.retry() },
            footer = CampLoadStateAdapter { liveAdapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.rows.collectLatest { pagedData ->
                liveAdapter.submitData(pagedData)
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
    fun onCellClickListenerLiveEvent(response: Row) {
        var mUrl = "https://www.bing.com/search?q=" + response.COURSE_NAME
        activity !!.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mUrl)))
    }
}