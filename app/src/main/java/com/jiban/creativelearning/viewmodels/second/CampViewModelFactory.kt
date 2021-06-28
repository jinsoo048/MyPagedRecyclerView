package com.jiban.creativelearning.viewmodels.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.second.CampApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class CampViewModelFactory(
    private val api: CampApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CampViewModel(api) as T
    }

}