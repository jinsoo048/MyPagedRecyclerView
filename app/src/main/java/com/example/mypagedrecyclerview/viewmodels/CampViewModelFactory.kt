package com.example.mypagedrecyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypagedrecyclerview.api.MyApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class CampViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CampViewModel(api) as T
    }

}