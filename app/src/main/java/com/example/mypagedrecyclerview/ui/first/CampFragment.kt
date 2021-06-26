package com.example.mypagedrecyclerview.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypagedrecyclerview.adpater.CampAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.mypagedrecyclerview.adpater.CampLoadStateAdapter
import com.example.mypagedrecyclerview.api.MyApi
import com.example.mypagedrecyclerview.databinding.FragmentCampBinding
import com.example.mypagedrecyclerview.viewmodels.CampViewModel
import com.example.mypagedrecyclerview.viewmodels.CampViewModelFactory


class CampFragment : Fragment() {

    private lateinit var viewModel: CampViewModel
    private lateinit var binding: FragmentCampBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCampBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = CampViewModelFactory(MyApi())
        viewModel = ViewModelProvider(this, factory).get(CampViewModel::class.java)

        val campAdapter = CampAdapter()
        //binding.campRv.layoutManager = LinearLayoutManager(requireContext())
        binding.campRv.layoutManager = GridLayoutManager(context,2)

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
}