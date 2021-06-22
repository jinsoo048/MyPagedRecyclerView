package com.example.mypagedrecyclerview.ui

import android.os.Bundle
import android.view.View
import com.example.mypagedrecyclerview.R
import com.example.mypagedrecyclerview.databinding.FragmentSecondBinding
import com.example.mypagedrecyclerview.x.config.BaseFragment

class SecondFragment :
    BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::bind, R.layout.fragment_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}