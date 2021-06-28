package com.jiban.creativelearning.viewmodels.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.first.EducationApi
import com.jiban.creativelearning.data.first.EducationDataSource

class EducationViewModel(
    private val api: EducationApi
) : ViewModel() {
    val rows = Pager(PagingConfig(pageSize = 10)) {
        EducationDataSource(api)
    }.flow.cachedIn(viewModelScope)
}