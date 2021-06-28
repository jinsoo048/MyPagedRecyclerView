package com.jiban.creativelearning.viewmodels.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.second.TravelApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class TravelViewModelFactory(
    private val api: TravelApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TravelViewModel(api) as T
    }

}