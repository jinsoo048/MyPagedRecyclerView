package com.jiban.creativelearning.ui.first

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jiban.creativelearning.adpater.first.CampLoadStateAdapter
import com.jiban.creativelearning.adpater.first.CultureAdapter
import com.jiban.creativelearning.api.first.CultureApi
import com.jiban.creativelearning.model.first.culture.Row
import com.jiban.creativelearning.viewmodels.first.CultureViewModel
import com.jiban.creativelearning.viewmodels.first.CultureViewModelFactory
import com.jiban.creativelearning.x.config.BaseFragment
import com.jiban.creativelearning.R
import com.jiban.creativelearning.databinding.FragmentCultureBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CultureFragment :
    BaseFragment<FragmentCultureBinding>(FragmentCultureBinding::bind, R.layout.fragment_culture) {

    private lateinit var viewModel: CultureViewModel
    private var activity: Activity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onGetCultureSuccess()
    }

    private fun onGetCultureSuccess() {

        val factory = CultureViewModelFactory(CultureApi())
        viewModel = ViewModelProvider(this, factory).get(CultureViewModel::class.java)

        val cultureAdapter = CultureAdapter(context = CultureFragment(), this)
        //binding.cultureRv.layoutManager = LinearLayoutManager(requireContext())
        binding.cultureRv.layoutManager = GridLayoutManager(context, 2)

        binding.cultureRv.setHasFixedSize(true)

        binding.cultureRv.adapter = cultureAdapter.withLoadStateHeaderAndFooter(
            header = CampLoadStateAdapter { cultureAdapter.retry() },
            footer = CampLoadStateAdapter { cultureAdapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.rows.collectLatest { pagedData ->
                cultureAdapter.submitData(pagedData)
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
    fun onCellClickListenerCultureEvent(response: Row) {

        if (activity != null && isAdded) {
            activity !!.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(response.SVCURL)),
                Bundle()
            )

        } else {
            Log.d(
                "JJS",
                "CultureFragment/onCellClickListenerCultureEvent: activity is null !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
            )
        }


    }
}