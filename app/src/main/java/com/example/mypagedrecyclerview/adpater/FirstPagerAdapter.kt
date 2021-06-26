package com.example.mypagedrecyclerview.adpater
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mypagedrecyclerview.ui.first.CampFragment
import com.example.mypagedrecyclerview.ui.first.EarthFragment
import com.example.mypagedrecyclerview.ui.first.MoonFragment

private const val NUM_PAGES = 3

class FirstPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CampFragment()
            1 -> MoonFragment()
            2 -> EarthFragment()
            else -> CampFragment()
        }
    }
}
