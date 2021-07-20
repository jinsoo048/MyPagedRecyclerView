package com.jiban.creativelearning.ui.second

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jiban.creativelearning.R
import com.jiban.creativelearning.adpater.second.SecondPagerAdapter
import com.jiban.creativelearning.databinding.FragmentSecondBinding
import com.jiban.creativelearning.x.config.BaseFragment

class SecondFragment :
    BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::bind, R.layout.fragment_second) {

    private lateinit var secondPagerAdapter: SecondPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondPagerAdapter = SecondPagerAdapter(childFragmentManager, lifecycle)
        viewPager = binding.secondVp
        viewPager.adapter = secondPagerAdapter

        val tabName = arrayOf<String>("여행", "캠프")

        TabLayoutMediator(binding.secondTab, binding.secondVp) { tab, position ->
            tab.text = tabName[position].toString()
        }.attach()

        //binding.secondTab.tabMode = TabLayout.MODE_SCROLLABLE
        //binding.secondTab.tabGravity = TabLayout.GRAVITY_CENTER

        binding.secondTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.secondVp.currentItem = tab !!.position
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