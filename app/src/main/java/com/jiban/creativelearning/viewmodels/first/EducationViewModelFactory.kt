package com.jiban.creativelearning.viewmodels.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiban.creativelearning.api.first.EducationApi

@Suppress("UNCHECKED_CASE", "UNCHECKED_CAST")
class EducationViewModelFactory(
    private val api: EducationApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EducationViewModel(api) as T
    }
}