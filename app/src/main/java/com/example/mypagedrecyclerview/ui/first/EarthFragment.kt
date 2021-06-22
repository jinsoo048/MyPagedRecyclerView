package com.example.mypagedrecyclerview.ui.first

import android.os.Bundle
import android.view.View
import com.example.mypagedrecyclerview.R
import com.example.mypagedrecyclerview.databinding.FragmentEarthBinding
import com.example.mypagedrecyclerview.x.config.BaseFragment

class EarthFragment :
    BaseFragment<FragmentEarthBinding>(FragmentEarthBinding::bind, R.layout.fragment_earth) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}