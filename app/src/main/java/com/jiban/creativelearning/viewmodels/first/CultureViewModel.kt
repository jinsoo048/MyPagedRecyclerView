package com.jiban.creativelearning.viewmodels.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.first.CultureApi
import com.jiban.creativelearning.data.first.CultureDataSource

class CultureViewModel(
    private val api: CultureApi
) : ViewModel() {
    val rows = Pager(PagingConfig(pageSize = 10)) {
        CultureDataSource(api)
    }.flow.cachedIn(viewModelScope)
}