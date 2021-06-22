package com.example.mypagedrecyclerview.adpater
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mypagedrecyclerview.ui.first.FirstFragment
import com.example.mypagedrecyclerview.ui.SecondFragment
import com.example.mypagedrecyclerview.ui.ThirdFragment
import com.example.mypagedrecyclerview.ui.first.EarthFragment
import com.example.mypagedrecyclerview.ui.first.MoonFragment
import com.example.mypagedrecyclerview.ui.first.SunFragment

private const val NUM_PAGES = 3

class FirstPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SunFragment()
            1 -> MoonFragment()
            2 -> EarthFragment()
            else -> SunFragment()
        }
    }
}
