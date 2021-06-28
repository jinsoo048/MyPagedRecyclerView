package com.jiban.creativelearning.ui.first

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.jiban.creativelearning.adpater.first.FirstPagerAdapter
import com.jiban.creativelearning.x.config.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jiban.creativelearning.R
import com.jiban.creativelearning.databinding.FragmentFirstBinding

class FirstFragment :
    BaseFragment<FragmentFirstBinding>(FragmentFirstBinding::bind, R.layout.fragment_first) {

    private lateinit var firstPagerAdapter: FirstPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstPagerAdapter = FirstPagerAdapter(childFragmentManager, lifecycle)
        viewPager = binding.firstVp
        viewPager.adapter = firstPagerAdapter

        val tabName = arrayOf<String>("문화체험", "학습체험")

        TabLayoutMediator(binding.firstTab, binding.firstVp) { tab, position ->
            tab.text = tabName[position].toString()
        }.attach()

        binding.firstTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.firstVp.currentItem = tab !!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("JJS", "onTabUnselected !!!!!!!!!!!!!!!!!")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("JJS", "onTabReselected !!!!!!!!!!!!!!!!!")
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}