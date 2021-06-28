package com.jiban.creativelearning.viewmodels.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.first.CultureApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class CultureViewModelFactory(
    private val api: CultureApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CultureViewModel(api) as T
    }

}