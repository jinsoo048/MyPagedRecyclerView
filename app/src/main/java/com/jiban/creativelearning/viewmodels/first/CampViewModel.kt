package com.jiban.creativelearning.viewmodels.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.first.MyApi
import com.jiban.creativelearning.data.first.CampDataSource

class CampViewModel(
    private val api: MyApi
) : ViewModel() {
    val items = Pager(PagingConfig(pageSize = 10)) {
        CampDataSource(api)
    }.flow.cachedIn(viewModelScope)
}