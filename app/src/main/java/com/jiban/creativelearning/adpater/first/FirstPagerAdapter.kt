package com.jiban.creativelearning.adpater.first

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jiban.creativelearning.ui.first.CultureFragment
import com.jiban.creativelearning.ui.first.EducationFragment

private const val NUM_PAGES = 2

class FirstPagerAdapter(childFragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        childFragmentManager, lifecycle
    ) {

    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CultureFragment()
            1 -> EducationFragment()
            else -> CultureFragment()
        }
    }
}
