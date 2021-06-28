package com.jiban.creativelearning.viewmodels.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.second.CampApi
import com.jiban.creativelearning.data.second.CampDataSource

class CampViewModel(
    private val api: CampApi
) : ViewModel() {
    val items = Pager(PagingConfig(pageSize = 10)) {
        CampDataSource(api)
    }.flow.cachedIn(viewModelScope)
}