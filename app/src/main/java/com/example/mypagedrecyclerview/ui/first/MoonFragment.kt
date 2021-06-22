package com.example.mypagedrecyclerview.ui.first

import android.os.Bundle
import android.view.View
import com.example.mypagedrecyclerview.R
import com.example.mypagedrecyclerview.databinding.FragmentMoonBinding
import com.example.mypagedrecyclerview.x.config.BaseFragment

class MoonFragment :
    BaseFragment<FragmentMoonBinding>(FragmentMoonBinding::bind, R.layout.fragment_moon) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}