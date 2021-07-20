package com.jiban.creativelearning.viewmodels.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.third.LiveApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class LiveViewModelFactory(
    private val api: LiveApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LiveViewModel(api) as T
    }
}