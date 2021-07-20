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
import com.jiban.creativelearning.adpater.third.DosungAdapter
import com.jiban.creativelearning.api.third.DosungApi
import com.jiban.creativelearning.databinding.FragmentDosungBinding
import com.jiban.creativelearning.model.third.dosung.Row
import com.jiban.creativelearning.viewmodels.third.DosungViewModel
import com.jiban.creativelearning.viewmodels.third.DosungViewModelFactory
import com.jiban.creativelearning.x.config.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class DosungFragment :
    BaseFragment<FragmentDosungBinding>(FragmentDosungBinding::bind, R.layout.fragment_dosung) {

    private lateinit var viewModel: DosungViewModel
    private var activity: Activity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onGetDosungSuccess()
    }

    private fun onGetDosungSuccess() {

        val factory = DosungViewModelFactory(DosungApi())
        viewModel = ViewModelProvider(this, factory).get(DosungViewModel::class.java)

        val dosungAdapter = DosungAdapter(context = DosungFragment(), this)
        //binding.dosungRv.layoutManager = LinearLayoutManager(requireContext())
        binding.dosungRv.layoutManager = GridLayoutManager(context, 2)

        binding.dosungRv.setHasFixedSize(true)

        binding.dosungRv.adapter = dosungAdapter.withLoadStateHeaderAndFooter(
            header = CampLoadStateAdapter { dosungAdapter.retry() },
            footer = CampLoadStateAdapter { dosungAdapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.rows.collectLatest { pagedData ->
                dosungAdapter.submitData(pagedData)
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
    fun onCellClickListenerDosungEvent(response: Row) {
        var mUrl = "https://www.bing.com/search?q=" + response.COURSE_NAME
        activity !!.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mUrl)))
    }
}