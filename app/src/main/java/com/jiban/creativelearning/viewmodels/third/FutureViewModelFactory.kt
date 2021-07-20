package com.jiban.creativelearning.viewmodels.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.third.FutureApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class FutureViewModelFactory(
    private val api: FutureApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureViewModel(api) as T
    }
}