package com.example.mypagedrecyclerview.ui.first

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.mypagedrecyclerview.R
import com.example.mypagedrecyclerview.adpater.FirstPagerAdapter
import com.example.mypagedrecyclerview.databinding.FragmentFirstBinding
import com.example.mypagedrecyclerview.x.config.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FirstFragment :
    BaseFragment<FragmentFirstBinding>(FragmentFirstBinding::bind, R.layout.fragment_first)
{

    private lateinit var firstPagerAdapter: FirstPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //HomeService(this).tryGetCultureFirst()
        firstPagerAdapter = FirstPagerAdapter(this)
        viewPager = binding.firstVp
        viewPager.adapter = firstPagerAdapter

        val tabName = arrayOf<String>("SUN", "Moon","Earth")

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