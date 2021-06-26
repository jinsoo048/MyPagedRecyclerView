package com.example.mypagedrecyclerview.adpater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mypagedrecyclerview.ui.first.CampFragment
import com.example.mypagedrecyclerview.ui.first.EarthFragment
import com.example.mypagedrecyclerview.ui.first.MoonFragment

private const val NUM_PAGES = 3

class FirstPagerAdapter(fragmentManager: FragmentManager?, lifecycle:Lifecycle) : FragmentStateAdapter(
    fragmentManager !!,lifecycle) {
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
