package com.jiban.creativelearning.ui.third

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jiban.creativelearning.R
import com.jiban.creativelearning.adpater.third.ThirdPagerAdapter
import com.jiban.creativelearning.databinding.FragmentThirdBinding
import com.jiban.creativelearning.x.config.BaseFragment

class ThirdFragment :
    BaseFragment<FragmentThirdBinding>(FragmentThirdBinding::bind, R.layout.fragment_third) {

    private lateinit var thirdPagerAdapter: ThirdPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thirdPagerAdapter = ThirdPagerAdapter(childFragmentManager, lifecycle)
        viewPager = binding.thirdVp
        viewPager.adapter = thirdPagerAdapter

        val tabName = arrayOf<String>("미래유산길", "생태문화길","한성도성길")

        TabLayoutMediator(binding.thirdTab, binding.thirdVp) { tab, position ->
            tab.text = tabName[position].toString()
        }.attach()

        binding.thirdTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.thirdVp.currentItem = tab !!.position
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