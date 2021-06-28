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
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiban.creativelearning.adpater.first.CampLoadStateAdapter
import com.jiban.creativelearning.adpater.second.TravelAdapter
import com.jiban.creativelearning.api.second.TravelApi
import com.jiban.creativelearning.model.second.travel.Item
import com.jiban.creativelearning.viewmodels.second.TravelViewModel
import com.jiban.creativelearning.viewmodels.second.TravelViewModelFactory
import com.jiban.creativelearning.x.config.BaseFragment
import com.jiban.creativelearning.R
import com.jiban.creativelearning.databinding.FragmentTravelBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TravelFragment :
    BaseFragment<FragmentTravelBinding>(FragmentTravelBinding::bind, R.layout.fragment_travel) {

    private lateinit var viewModel: TravelViewModel
    private var activity: Activity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onGetTravelSuccess()
    }

    private fun onGetTravelSuccess() {

        val factory = TravelViewModelFactory(TravelApi())
        viewModel = ViewModelProvider(this, factory).get(TravelViewModel::class.java)

        val travelAdapter = TravelAdapter(context = TravelFragment(), this)
        binding.travelRv.layoutManager = LinearLayoutManager(requireContext())
        //binding.travelRv.layoutManager = GridLayoutManager(context, 2)

        binding.travelRv.setHasFixedSize(true)

        binding.travelRv.adapter = travelAdapter.withLoadStateHeaderAndFooter(
            header = CampLoadStateAdapter { travelAdapter.retry() },
            footer = CampLoadStateAdapter { travelAdapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.items.collectLatest { pagedData ->
                travelAdapter.submitData(pagedData)
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
    fun onCellClickListenerTravelEvent(response: Item) {
        var mUrl = "https://www.bing.com/search?q=" + response.galTitle
        activity !!.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mUrl)))

    }
}