package com.example.mypagedrecyclerview.ui.first

import android.os.Bundle
import android.view.View
import com.example.mypagedrecyclerview.R
import com.example.mypagedrecyclerview.databinding.FragmentSunBinding
import com.example.mypagedrecyclerview.x.config.BaseFragment

class SunFragment :
    BaseFragment<FragmentSunBinding>(FragmentSunBinding::bind, R.layout.fragment_sun) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}