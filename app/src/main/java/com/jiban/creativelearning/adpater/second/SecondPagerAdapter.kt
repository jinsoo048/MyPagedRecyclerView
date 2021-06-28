package com.jiban.creativelearning.adpater.second
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jiban.creativelearning.ui.second.CampFragment
import com.jiban.creativelearning.ui.second.TravelFragment

private const val NUM_PAGES = 2

class SecondPagerAdapter(childFragmentManager: FragmentManager, lifecycle:Lifecycle) : FragmentStateAdapter(
    childFragmentManager,lifecycle) {

    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TravelFragment()
            1 -> CampFragment()
            else -> TravelFragment()
        }
    }
}
