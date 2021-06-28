package com.jiban.creativelearning.viewmodels.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.first.MyApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class CampViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CampViewModel(api) as T
    }

}