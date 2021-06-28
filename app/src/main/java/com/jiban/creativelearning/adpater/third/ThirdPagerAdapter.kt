package com.jiban.creativelearning.adpater.third
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jiban.creativelearning.ui.third.DosungFragment
import com.jiban.creativelearning.ui.third.FutureFragment
import com.jiban.creativelearning.ui.third.LiveFragment

private const val NUM_PAGES = 3

class ThirdPagerAdapter(childFragmentManager: FragmentManager, lifecycle:Lifecycle) : FragmentStateAdapter(
    childFragmentManager,lifecycle) {

    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FutureFragment()
            1 -> LiveFragment()
            2 -> DosungFragment()
            else -> FutureFragment()
        }
    }
}
