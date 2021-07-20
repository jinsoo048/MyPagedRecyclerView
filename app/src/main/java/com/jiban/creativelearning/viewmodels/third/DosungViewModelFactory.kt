package com.jiban.creativelearning.viewmodels.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.third.DosungApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class DosungViewModelFactory(
    private val api: DosungApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DosungViewModel(api) as T
    }
}