package com.example.mypagedrecyclerview.ui

import android.os.Bundle
import android.view.View
import com.example.mypagedrecyclerview.R
import com.example.mypagedrecyclerview.databinding.FragmentThirdBinding
import com.example.mypagedrecyclerview.x.config.BaseFragment

class ThirdFragment :
    BaseFragment<FragmentThirdBinding>(FragmentThirdBinding::bind, R.layout.fragment_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}